package vn.com.vndirect.commons.convert;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;

import vn.com.web.commons.domain.db.PagingInfo;
import vn.com.web.commons.utility.DateUtils;

public class ServiceBeanConverter {

	@SuppressWarnings("unchecked")
	public static <T> T newInstance(Class<?> clazz) {
		try {
			Object newobj = clazz.getConstructor().newInstance();
			return (T) newobj; // unchecked cast
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static <S, T> List<T> getList(List<S> sourceList, Class<T> actualEditable) {
		return getList(sourceList, actualEditable, null);
	}

	public static <S, T> List<T> getList(List<S> sourceList, Class<T> actualEditable, String[] ignoreProperties) {
		List<T> resultList = new ArrayList<T>();

		if (sourceList != null && sourceList.size() > 0) {
			for (S s : sourceList) {
				T target = get(s, actualEditable, ignoreProperties);
				resultList.add(target);
			}
		}

		return resultList;
	}

	public static <S, T> T get(S source, Class<T> actualEditable) {
		return get(source, actualEditable, null);
	}

	public static <S, T> T get(S source, Class<T> actualEditable, String[] ignoreProperties) {
		if (source == null) {
			return newInstance(actualEditable);
		}

		T target = newInstance(actualEditable);
		PropertyDescriptor targetPds[] = BeanUtils.getPropertyDescriptors(actualEditable);
		List<String> ignoreList = ignoreProperties == null ? null : Arrays.asList(ignoreProperties);

		for (int i = 0; i < targetPds.length; i++) {
			PropertyDescriptor targetPd = targetPds[i];
			if (targetPd.getWriteMethod() == null || ignoreProperties != null && ignoreList.contains(targetPd.getName()))
				continue;
			PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(source.getClass(), targetPd.getName());
			if (sourcePd == null || sourcePd.getReadMethod() == null)
				continue;

			try {

				Method readMethod = sourcePd.getReadMethod();
				if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers()))
					readMethod.setAccessible(true);
				Object value = readMethod.invoke(source, new Object[0]);
				Method writeMethod = targetPd.getWriteMethod();
				if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers()))
					writeMethod.setAccessible(true);

				if (targetPd.getPropertyType() == Date.class && sourcePd.getPropertyType() == Calendar.class) {
					writeMethod.invoke(target, new Object[] { DateUtils.getCalendarDate((Calendar) value) });
				} else if (targetPd.getPropertyType() == Calendar.class && sourcePd.getPropertyType() == Date.class) {
					writeMethod.invoke(target, new Object[] { DateUtils.getCalendar((Date) value) });
				} else {
					// default
					writeMethod.invoke(target, new Object[] { value });
				}
			} catch (Throwable ex) {
				throw new FatalBeanException("Could not copy properties from source to target", ex);
			}
		}

		return target;
	}

	public static <S, T> T merge(S source, T target, Class<T> actualEditable) {
		return merge(source, target, actualEditable, null);
	}

	public static <S, T> T merge(S source, T target, Class<T> actualEditable, String[] ignoreProperties) {
		if (source == null && target == null) {
			return newInstance(actualEditable);
		} else if (source == null) {
			return target;
		}

		PropertyDescriptor targetPds[] = BeanUtils.getPropertyDescriptors(actualEditable);
		List<String> ignoreList = ignoreProperties == null ? null : Arrays.asList(ignoreProperties);

		for (int i = 0; i < targetPds.length; i++) {
			PropertyDescriptor targetPd = targetPds[i];
			if (targetPd.getWriteMethod() == null || ignoreProperties != null && ignoreList.contains(targetPd.getName()))
				continue;
			PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(source.getClass(), targetPd.getName());
			if (sourcePd == null || sourcePd.getReadMethod() == null)
				continue;

			try {
				Method readMethod = sourcePd.getReadMethod();
				if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers()))
					readMethod.setAccessible(true);
				Object value = readMethod.invoke(source, new Object[0]);
				Method writeMethod = targetPd.getWriteMethod();
				if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers()))
					writeMethod.setAccessible(true);

				if (value != null) {
					if (targetPd.getPropertyType() == Date.class && sourcePd.getPropertyType() == Calendar.class) {
						writeMethod.invoke(target, new Object[] { DateUtils.getCalendarDate((Calendar) value) });
					} else if (targetPd.getPropertyType() == Calendar.class && sourcePd.getPropertyType() == Date.class) {
						writeMethod.invoke(target, new Object[] { DateUtils.getCalendar((Date) value) });
					} else {
						// default
						writeMethod.invoke(target, new Object[] { value });
					}
				}
			} catch (Throwable ex) {
				throw new FatalBeanException("Could not merge properties from source to target", ex);
			}
		}

		return target;
	}

	/**
	 * convert model paging to service paging
	 * 
	 * @param modelPaging
	 * @return
	 */
	public static vn.com.vndirect.wsclient.PagingInfo convertModelPagingToServicePaging(PagingInfo modelPaging) {
		vn.com.vndirect.wsclient.PagingInfo servicePaging = new vn.com.vndirect.wsclient.PagingInfo();
		if (modelPaging != null) {
			servicePaging.setCurrentIndex((modelPaging.getIndexPage() - 1) * modelPaging.getOffset() + 1);
			servicePaging.setOffsetNumber(modelPaging.getOffset());
			// servicePaging.setTotalItem(((Long)modelPaging.getTotal()).intValue());
		}
		return servicePaging;
	}

	/**
	 * convert service paging to model paging
	 * 
	 * @param servicePaging
	 * @return
	 */
	public static PagingInfo convertServicePagingToModelPaging(vn.com.vndirect.wsclient.PagingInfo servicePaging) {
		PagingInfo modelPaging = new PagingInfo();
		if (servicePaging != null) {
			int index = servicePaging.getCurrentIndex() == null ? PagingInfo.DEFAULT_INDEX : servicePaging.getCurrentIndex().intValue();
			int offset = servicePaging.getOffsetNumber() == null ? PagingInfo.DEFAULT_OFFSET : servicePaging.getOffsetNumber().intValue();
			int total = servicePaging.getTotalItem() == null ? 0 : servicePaging.getTotalItem().intValue();

			modelPaging.setIndexPage(index / offset + 1);
			modelPaging.setOffset(offset);
			modelPaging.setTotal(total);
		}
		return modelPaging;
	}

	// public static void main(String[] args) throws Exception {
	// System.out.println("BEGIN");
	//
	// /** copy single **/
	// Account account = new Account();
	// account.setUsername("Pham Vu Thang");
	// AccountVO accountVO = get(account, AccountVO.class);
	// System.out.println("account.username:" + account.getUsername() + "--" + "accountVO.getUsername():" + accountVO.getUsername());
	//
	// /** copy list **/
	// List<Account> listAccount = new ArrayList<Account>();
	// Account account1 = new Account();
	// account1.setUsername("Pham Vu Thang 1");
	// Account account2 = new Account();
	// account2.setUsername("Pham Vu Thang 2");
	//
	// listAccount.add(account);
	// listAccount.add(account1);
	// listAccount.add(account2);
	//
	// List<AccountVO> listAccountVO = getList(listAccount, AccountVO.class);
	// System.out.println("Before list");
	// int i = 0;
	// for (AccountVO value : listAccountVO) {
	// System.out.println("In loop " + (++i) + ":" + value.getUsername());
	// }
	// System.out.println("After list");
	//
	// /** merge propety **/
	// Account accountM1 = new Account();
	// AccountVO accountVOM1 = new AccountVO();
	// //accountM1.setUsername("Pham Vu Thang");
	// accountVOM1.setUsername("Pham Vu Thang Org");
	// accountVOM1 = merge(accountM1, accountVOM1, AccountVO.class);
	// System.out.println("accountM1.username:" + accountM1.getUsername() + "--" + "accountVOM1.getUsername():" + accountVOM1.getUsername());
	//
	// System.out.println("END");
	// }
}
