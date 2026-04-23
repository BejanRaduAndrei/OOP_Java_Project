import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class ClientHistory {
    private TreeSet<ClientHistoryRecord> histories = new TreeSet<>(
        Comparator.comparing(ClientHistoryRecord::getActionDate)
    );

    public void addHistory(ClientHistoryRecord record) {
        if (record != null) {
            histories.add(record);
        }
    }

    public ClientHistoryRecord createHistory(String historyId, Client client, Booking booking, String actionDate, String action) {
        if (client == null) {
            return null;
        }
        ClientHistoryRecord record = new ClientHistoryRecord(historyId, client, booking, actionDate, action);
        histories.add(record);
        return record;
    }

    public List<ClientHistoryRecord> getAllHistory() {
        return new ArrayList<>(histories);
    }

    public List<ClientHistoryRecord> getHistoryByClient(Client client) {
        List<ClientHistoryRecord> result = new ArrayList<>();
        for (ClientHistoryRecord record : histories) {
            if (record.getClient().equals(client)) {
                result.add(record);
            }
        }
        return result;
    }

    public List<ClientHistoryRecord> getHistoryByAction(String action) {
        List<ClientHistoryRecord> result = new ArrayList<>();
        for (ClientHistoryRecord record : histories) {
            if (record.getAction().equals(action)) {
                result.add(record);
            }
        }
        return result;
    }

    public void displayAllHistory() {
        for (ClientHistoryRecord record : histories) {
            System.out.println(record);
        }
    }

    public void displayHistoryByClient(Client client) {
        for (ClientHistoryRecord record : getHistoryByClient(client)) {
            System.out.println(record);
        }
    }

    public static class ClientHistoryRecord {
        private String historyId;
        private Client client;
        private Booking booking;
        private String actionDate;
        private String action;

        public ClientHistoryRecord(String historyId, Client client, Booking booking, String actionDate, String action) {
            this.historyId = historyId;
            this.client = client;
            this.booking = booking;
            this.actionDate = actionDate;
            this.action = action;
        }

        public String getHistoryId() {
            return historyId;
        }

        public Client getClient() {
            return client;
        }

        public Booking getBooking() {
            return booking;
        }

        public String getActionDate() {
            return actionDate;
        }

        public String getAction() {
            return action;
        }

        @Override
        public String toString() {
            return "ClientHistoryRecord{" +
                    "historyId='" + historyId + '\'' +
                    ", client=" + client.getName() +
                    ", booking=" + booking +
                    ", actionDate='" + actionDate + '\'' +
                    ", action='" + action + '\'' +
                    '}';
        }
    }
}
