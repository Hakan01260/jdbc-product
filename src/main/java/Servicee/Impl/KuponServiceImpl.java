package Servicee.Impl;

import Service.KuponService;
import com.proje.model.Kupon;
import com00proje.repository.Impl.KuponRepositoryImpl;
import com__proje.repository.KuponRepository;

import java.util.List;

public class KuponServiceImpl implements KuponService {

    private KuponRepository kuponRepository = new KuponRepositoryImpl();

    @Override
    public Kupon saveKupon(Kupon kupon) {
        return kuponRepository.saveKupon(kupon);
    }

    @Override
    public boolean saveKuponUser(int kuponId, int userId) {
        return kuponRepository.saveKuponUser(kuponId,userId);
    }

    @Override
    public Kupon updateKupon(Kupon kupon) {
        return kuponRepository.updateKupon(kupon);
    }

    @Override
    public boolean removeKupon(int kuponId) {
        return kuponRepository.removeKupon(kuponId);
    }

    @Override
    public Kupon findKuponById(int kuponId) {
        return kuponRepository.findKuponById(kuponId);
    }

    @Override
    public Kupon findKuponUsersById(int kuponId) {
        return kuponRepository.findKuponUsersById(kuponId);
    }

    @Override
    public List<Kupon> findKupons() {
        return kuponRepository.findKupons();
    }
}
