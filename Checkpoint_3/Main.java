

class Main {
    ScoutingEntry[] scoutingEntry = new ScoutingEntry[3];

    public static void main(String[] args) {
        ScoutingEntry[0] = new PitScoutingEntry(67, "Aragon", "2026-07-18 16:42", "RWD", 34.6, true);
        ScoutingEntry[1] = new MatchScoutingEntry(5953, "Luke", "2026-01-23 15:50", 5, MatchPhase.TELEOP, 243);
        ScoutingEntry[2] = new MatchScoutingEntry(8888, "Agamemnon", "2026-10-10 14:30", 3, MatchPhase.ENDGAME, 250);

        for (int i = 0; i < ScoutingEntry.length; i++) {
            System.out.println(ScoutingEntry[i].getSummaryHeader());
            System.out.println(ScoutingEntry[i].getDetails());
            if (ScoutingEntry[i] instanceof Exportable) {
                System.out.println(ScoutingEntry[i].toCsvRow());
            }
        }
        
        try {
            new MatchScoutingEntry(5555, "Spider", "2026-06-11", 2, MatchPhase.AUTONOMOUS, -132);
        } catch(InvalidScoutingDataException e) {
            System.out.println("A team can not score negative points.");
        }
    }
}