package database;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class DeltagerDAO{

	@PersistenceContext(name = "deltagerPU")
	private EntityManager em;
	
	public void leggTilDeltager(Deltager deltager) {
		em.persist(deltager);
	}
	
	public void fjernDeltager(Deltager deltager) {
		em.remove(em.merge(deltager));
	}
	
	public List<Deltager> hentDeltagerListe(){
		return em.createQuery("SELECT d FROM Deltager d", Deltager.class).getResultList();
	}
	
	public Deltager hentDeltager(String mobil) {
		return em.find(Deltager.class, mobil);
	}
}
