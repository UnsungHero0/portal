/* Vietnam initialisation for the jQuery UI date picker plugin. */

jQuery(function($){
	$.datepicker.regional['vn'] = {
			closeText: 'Ho\u00E0n th\u00E0nh', // Display text for close link
			prevText: 'Tr\u01B0\u1EDBc', // Display text for previous month link
			nextText: 'Ti\u1EBFp', // Display text for next month link
			currentText: 'H\u00F4m nay', // Display text for current month link
			monthNames: ['Th\u00E1ng 1','Th\u00E1ng 2','Th\u00E1ng 3','Th\u00E1ng 4','Th\u00E1ng 5','Th\u00E1ng 6',
				'Th\u00E1ng 7','Th\u00E1ng 8','Th\u00E1ng 9','Th\u00E1ng 10','Th\u00E1ng 11','Th\u00E1ng 12'], // Names of months for drop-down and formatting
			monthNamesShort: ['Th 1', 'Th 2', 'Th 3', 'Th 4', 'Th 5', 'Th 6', 'Th 7', 'Th 8', 'Th 9', 'Th 10', 'Th 11', 'Th 12'], // For formatting
			dayNames: ['Ch\u1EE7 nh\u1EADt', 'Th\u1EE9 2', 'Th\u1EE9 3', 'Th\u1EE9 4', 'Th\u1EE9 5', 'Th\u1EE9 6', 'Th\u1EE9 7'], // For formatting
			dayNamesShort: ['CN','T2','T3','T4','T5','T6','T7'], // For formatting
			dayNamesMin: ['CN','T2','T3','T4','T5','T6','T7'], // Column headings for days starting at Sunday
			dateFormat: 'dd/mm/yy', // See format options on parseDate
			firstDay: 0, // The first day of the week, Sun = 0, Mon = 1, ...
			isRTL: false, // True if right-to-left language, false if left-to-right
		showMonthAfterYear: true};
	$.datepicker.setDefaults($.datepicker.regional['vn']);
});