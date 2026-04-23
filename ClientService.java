import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClientService {
    private final List<Client> clients = new ArrayList<>();
    private final Set<String> cnpIndex = new HashSet<>();

    public void addClient(Client client) {
        if (client == null || client.getCnp() == null || cnpIndex.contains(client.getCnp())) {
            return;
        }
        clients.add(client);
        cnpIndex.add(client.getCnp());
    }

    public Client createClient(String name, String email, String cnp, String phoneNumber) {
        if (cnp == null || cnpIndex.contains(cnp)) {
            return null;
        }
        Client client = new Client(name, email, cnp, phoneNumber);
        clients.add(client);
        cnpIndex.add(cnp);
        return client;
    }

    public List<Client> getAllClients() {
        return clients;
    }

    public Client findClientByCnp(String cnp) {
        for (Client client : clients) {
            if (client.getCnp().equals(cnp)) {
                return client;
            }
        }
        return null;
    }

    public boolean removeClientByCnp(String cnp) {
        Client client = findClientByCnp(cnp);
        if (client != null) {
            clients.remove(client);
            cnpIndex.remove(cnp);
            return true;
        }
        return false;
    }

    public void displayClients() {
        for (Client client : clients) {
            System.out.println(client);
        }
    }
}
