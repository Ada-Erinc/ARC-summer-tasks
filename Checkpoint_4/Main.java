import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Main{

    private static void printTeamStats(List<Team> teamList) {
        System.out.println("----------------------------------------------------------------------");
        for (int i = 0; i < teamList.size(); i++) {
            System.out.println(teamList.get(i));
        }
        System.out.println("----------------------------------------------------------------------");
    }

    public static void main(String[] args) {
        List<Team> loadedTeams = Team.loadTeams("teams.csv");
        printTeamStats(loadedTeams);
        Map<Integer, Team> teams = new HashMap<Integer,Team>();
        for (int i = 0; i < loadedTeams.size(); i++) {
            teams.put(loadedTeams.get(i).getTeamNumber(), loadedTeams.get(i));
        }

        //Every team plays once
        Team.recordMatchResult(teams, 7891, 6645, 100.0, 16.0);
        Team.recordMatchResult(teams, 8671, 7057, 45.0, 44.0);
        Team.recordMatchResult(teams, 7265, 2566, 94.0, 82.0);
        Team.recordMatchResult(teams, 1900, 5969, 72.0, 69.0);
        //Every team plays a second time
        Team.recordMatchResult(teams, 6645, 1900, 54.0, 32.0);
        Team.recordMatchResult(teams, 2566, 8671, 44.0, 38.0);
        Team.recordMatchResult(teams, 5969, 7057, 37.0, 24.0);
        Team.recordMatchResult(teams, 7891, 7265, 54.0, 44.0);

        printTeamStats(loadedTeams);

        List<Team> teamList = new ArrayList<Team>();
        for (int teamNum : teams.keySet()) {
            teamList.add(teams.get(teamNum));
        }
        Collections.sort(teamList);

        for (int i = 0; i < teamList.size(); i++) {
            System.out.println((i+1) + ". " + teamList.get(i).getTeamName() + " (" + teamList.get(i).getTeamNumber() + ")    " + teamList.get(i).getPoints() + " points");
        }

        Team.exportStandings(teamList, "standings.csv");

        teamList.sort(Comparator.comparing(Team::getWins).reversed().thenComparingDouble(Team::getPoints));
        System.out.println("----------------------------------------------------------------------");
        for (int i = 0; i < teamList.size(); i++) {
            System.out.println((i+1) + ". " + teamList.get(i).getTeamName() + " (" + teamList.get(i).getTeamNumber() + ")    " + teamList.get(i).getWins() + " wins");
        }
    }

}