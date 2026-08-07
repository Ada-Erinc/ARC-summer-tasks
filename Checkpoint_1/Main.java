import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int numTeams = getTeamCount();
        int[] teamNum = new int[numTeams];
        int[] teamAPs = new int[numTeams];
        int[] teamTPs = new int[numTeams];
        int[] teamEPs = new int[numTeams];
        getTeamPoints(numTeams, teamNum, teamAPs, teamTPs, teamEPs);
        scoreBoard(teamNum, teamAPs, teamTPs, teamEPs);
    }

    private static int getTeamCount() {
        int numTeams = 0;
        while (true) {
            System.out.print("\nHow many teams are playing(2-6)? ");
            numTeams = scanner.nextInt();
            if (numTeams < 2) {
                System.out.print("\nThere should be at least 2 teams.");
            } else if (numTeams > 6) {
                System.out.print("\nThere should be maximum 6 teams.");
            } else {
                System.out.print("\n");
                break;
            }
        }
        return numTeams;
    }

    private static int getPositiveInt() {
        int num = scanner.nextInt();
        while (num < 0) {
            System.out.print("Please enter a positive number: ");
            num = scanner.nextInt();
        }
        return num;
    }

    private static void getTeamPoints(int numTeams, int[] teamNum, int[] teamAPs, int[] teamTPs, int[] teamEPs) {
        for (int i = 0 ; i < numTeams; i++) {
            System.out.print(  "------- Team " + (i + 1) + " -------");
            System.out.print("\nEnter team number: ");
            teamNum[i] = getPositiveInt();
            System.out.print("Enter auto points: ");
            teamAPs[i] = getPositiveInt();
            System.out.print("Enter teleop points: ");
            teamTPs[i] = getPositiveInt();
            System.out.print("Enter endgame points: ");
            teamEPs[i] = getPositiveInt();
        }
    }

    private static int calculateTotal(int auto, int teleop, int endgame) {
        int total = auto + teleop + endgame;
        return total;
    }

    private static int[] calculateTotal(int[] auto, int[] teleop, int[] endgame) {
        int[] total = new int[auto.length];
        for (int i = 0; i < auto.length; i++) {
            total[i] = calculateTotal(auto[i], teleop[i], endgame[i]);
        }
        return total;
    }

    private static double averageScore(int[] totals) {
        double average = 0;
        for (int i = 0; i < totals.length; i++) {
            average = average + totals[i];
        }
        average = average / totals.length;
        return average;
    }

    private static int highestScoringTeamIndex(int[] totals) {
        int index = 0;
        for (int i = 0; i < totals.length; i++) {
            if (totals[i] > totals[index]) {
                index = i;
            }
        }
        return index;
    }

    private static void scoreBoard(int[] teamNum, int[] teamAPs, int[] teamTPs, int[] teamEPs) {
        int[] calculateTotal = calculateTotal(teamAPs, teamTPs, teamEPs);
        double averageScore = averageScore(calculateTotal);
        int highestScoringTeamIndex = highestScoringTeamIndex(calculateTotal);

        System.out.print("\n");
        System.out.print("\n~~~~~~~~ MATCH RESULTS ~~~~~~~~");
        for (int i =0; i < teamNum.length; i++) {
            System.out.print("\nTeam " + teamNum[i] + ": " + calculateTotal[i] + " points" + 
            " (Auto: " + teamAPs[i] + ", Teleop: " + teamTPs[i] + ", Endgame: " + teamEPs[i] + ")");
        }
        System.out.print("\nAverage score: " + averageScore);
        System.out.print("\nHighest Scoring Team: " + teamNum[highestScoringTeamIndex]);
        System.out.print("\n____________________________");
    }
}