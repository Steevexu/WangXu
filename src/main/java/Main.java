import Classes.Formation;
import Classes.Pair;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args){
                // Test
                Pair pair = new Pair();

                // Date Preview
                LocalDate datePrevue = LocalDate.of(2023, 12, 31);

                // Date Remise
                LocalDate dateRemise = LocalDate.of(2024, 1, 5);

                pair.setDateRemiseReport(dateRemise);

                int pointsPenalty = pair.calculatePointsPenalty();

                System.out.println("Date prévue : " + datePrevue);
                System.out.println("Date remise : " + dateRemise);
                System.out.println("Penality points: " + pointsPenalty); // 5 points for 5 days

        }

}

