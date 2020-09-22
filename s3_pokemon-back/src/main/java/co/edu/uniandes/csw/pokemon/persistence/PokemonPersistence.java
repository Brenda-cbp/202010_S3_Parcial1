/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.csw.pokemon.persistence;

import co.edu.uniandes.csw.pokemon.entities.PokemonEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class PokemonPersistence {
private static final Logger LOGGER = Logger.getLogger(PokemonPersistence.class.getName());

    @PersistenceContext(unitName = "pokemonPU")
    protected EntityManager em;

   
    public PokemonEntity create(PokemonEntity pokemonEntity) {
        LOGGER.log(Level.INFO, "Creando un pokemon nuevo");
        em.persist(pokemonEntity);
        LOGGER.log(Level.INFO, "pokemon creado");
        return pokemonEntity;
    }

   
    public List< PokemonEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los autores");
        TypedQuery query = em.createQuery("select u from AuthorEntity u",  PokemonEntity.class);
          return query.getResultList();
    }

   
    public  PokemonEntity find(Long pokemonsId) {
        LOGGER.log(Level.INFO, "Consultando el autor con id={0}", pokemonsId);
        return em.find( PokemonEntity.class, pokemonsId);
    }

    public  PokemonEntity update( PokemonEntity  pokemonEntity) {
        LOGGER.log(Level.INFO, "Actualizando el author con id={0}", pokemonEntity.getId());
        return em.merge( pokemonEntity);
    }

    public void delete(Long authorsId) {

        LOGGER.log(Level.INFO, "Borrando el author con id={0}", authorsId);
         PokemonEntity authorEntity = em.find( PokemonEntity.class, authorsId);
        em.remove(authorEntity);
    }
}
