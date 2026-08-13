import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


class Team implements Comparable<Team> {
    int teamNumber;
    String teamName;
    int wins = 0;
    int losses = 0;
    double rankingPoints = 0;

    public Team(int teamNumber, String teamName) {
        this.teamNumber = teamNumber;
        this.teamName = teamName;
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public String getTeamName() {
        return teamName;
    }

    public double getPoints() {
        return rankingPoints;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public void recordWin() {
        wins += 1;
    }

    public void recordLoss() {
        losses += 1;
    }


    public void addRankingPoints(double points) {
        if (points < 0) {
            throw new IllegalArgumentException("Points must be positive.");
        }
        rankingPoints += points;
    }

    @Override
    public int compareTo(Team other) {
        return Double.compare(other.rankingPoints, this.rankingPoints);
    }

    @Override
    public String toString() {
        String data = "Team: " + teamName + " (" + teamNumber + "), Wins: " + wins + ", Losses: " + losses + ", Ranking points: " + rankingPoints;
        return data;
    }

    public static List<Team> loadTeams(String filepath) {
        List<Team> teamList = new ArrayList<Team>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            //Reading header.
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int teamNumber = Integer.parseInt(parts[0]);
                String teamName = parts[1];
                Team team = new Team(teamNumber, teamName);
                teamList.add(team);
            }
        } catch(IOException e) {
            System.out.println("There is a problem with the file.");
        }
        return teamList;
    }

    public static void recordMatchResult(Map<Integer, Team> teams, int winnerNumber, int loserNumber, double winnerPoints, double loserPoints) {
        if (!teams.containsKey(winnerNumber) || !teams.containsKey(loserNumber)) {
            System.out.println("The winner number or the loser number doesn't exist.");
            return;
        }
        teams.get(winnerNumber).recordWin();
        teams.get(loserNumber).recordLoss();

        teams.get(winnerNumber).addRankingPoints(winnerPoints);
        teams.get(loserNumber).addRankingPoints(loserPoints);
    }

    public static void exportStandings(List<Team> sortedTeams, String filepath) {
        // rank,teamNumber,teamName,wins,losses,rankingPoints

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
            writer.write("rank,teamNumber,teamName,wins,losses,rankingPoints");
            writer.newLine();
            for (int i = 0; i < sortedTeams.size(); i++) {
                writer.write((i+1) + "," + sortedTeams.get(i).getTeamNumber() + "," + sortedTeams.get(i).getTeamName() + "," + sortedTeams.get(i).getWins() + "," + sortedTeams.get(i).getLosses() + "," + sortedTeams.get(i).getPoints());
                writer.newLine();
            }
        } catch(IOException e) {
            System.out.println("There is a problem with the file.");
        }
    }

}