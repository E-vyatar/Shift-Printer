package org.example.shiftsprinter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class PrintShifts {

    public static void main(String[] args) {

        // Get the date string from user input in the console
//        System.out.println(args[0]);
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter a date (dd/MM/yyyy): ");
//        String dateString = scanner.nextLine();
        String dateString = args[0];
//        shiftPrinter(dateString);

    }

    public String shiftPrinter(LocalDate localDate) {
        // Define the date format for parsing
        SimpleDateFormat dateFormatDayMonthYear = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dateFormatDayMonthDayOfWeek = new SimpleDateFormat("dd/MM EEEE");
        SimpleDateFormat dateFormatDayMonth = new SimpleDateFormat("dd/MM");

        StringBuilder shiftsAvailability = new StringBuilder("Shifts Availability ");

        try {
            // Parse the input date string
            Date date = dateFormatDayMonthYear.parse(String.valueOf(localDate));

            // Create a Calendar instance and set it to the parsed date
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            // Adjust the starting day based on the parsed date
//            int startingDay = calendar.get(Calendar.DAY_OF_WEEK);

            StringBuilder shifts = new StringBuilder("Work around 32 hours a month.\n\n");
            int startMonth = calendar.get(Calendar.MONTH);
            Date startDate = calendar.getTime();

            int numOfDays = 7;

            // Iterate through the next seven days and print the formatted date and day
            for (int i = 0; i < numOfDays; i++) {
                shifts.append(dateFormatDayMonthDayOfWeek.format(calendar.getTime())).append(":\n");
                calendar.add(Calendar.DAY_OF_WEEK, 1); // Increment the day by 1
            }

            calendar.add(Calendar.DAY_OF_WEEK, -1);

            if (startMonth == calendar.get(Calendar.MONTH)) {
                shiftsAvailability.append(new SimpleDateFormat("dd").format(startDate));
            }
            else {
                shiftsAvailability.append(dateFormatDayMonth.format(startDate));
            }

            shiftsAvailability.append("-").append(dateFormatDayMonth.format(calendar.getTime())).append("\n").append(shifts);
//            System.out.println(shiftsAvailability.append(shifts));

        } catch (ParseException e) {
            System.err.println("Error parsing date: " + e.getMessage());
        }

        return shiftsAvailability.toString();
    }

    // Helper method to get the day of the week as a string
    private static String getDayOfWeek(Calendar calendar) {
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        return days[dayOfWeek - 1];
    }
}
