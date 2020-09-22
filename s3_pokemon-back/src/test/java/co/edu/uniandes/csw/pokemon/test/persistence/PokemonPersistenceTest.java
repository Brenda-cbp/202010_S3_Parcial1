/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.csw.pokemon.test.persistence;

import co.edu.uniandes.csw.pokemon.entities.PokemonEntity;
import co.edu.uniandes.csw.pokemon.persistence.PokemonPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@RunWith(Arquillian.class)
public class PokemonPersistenceTest {
 @Inject
    private PokemonPersistence pokemonPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<PokemonEntity> data = new ArrayList<>();

 
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PokemonEntity.class.getPackage())
                .addPackage(PokemonPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    private void clearData() {
        em.createQuery("delete from PokemonEntity").executeUpdate();
    }

   
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 13; i++) {
            PokemonEntity entity = factory.manufacturePojo(PokemonEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    @Test
    public void createPokemonTest() {
        PodamFactory factory = new PodamFactoryImpl();
        PokemonEntity newEntity = factory.manufacturePojo(PokemonEntity.class);
        PokemonEntity result = pokemonPersistence.create(newEntity);

        Assert.assertNotNull(result);

        PokemonEntity entity;
     entity = em.find(PokemonEntity.class, result.getId());

       // Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }
     /**
     * Prueba para consultar un Author.
     */
    @Test
    public void getPokemonTest() {
        PokemonEntity entity = data.get(0);
        PokemonEntity newEntity = pokemonPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
    }

}
