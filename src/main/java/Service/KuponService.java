package Service;

import com.proje.model.Kupon;

import java.util.List;

public interface KuponService {

    Kupon saveKupon(Kupon kupon);

    boolean saveKuponUser(int kuponId, int userId);

    Kupon updateKupon(Kupon kupon);

    boolean removeKupon(int kuponId);

    Kupon findKuponById(int kuponId);

    Kupon findKuponUsersById(int kuponId);

    List<Kupon> findKupons();
}
