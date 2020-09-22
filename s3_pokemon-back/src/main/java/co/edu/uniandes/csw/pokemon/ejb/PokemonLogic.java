/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.uniandes.csw.pokemon.ejb;

import co.edu.uniandes.csw.pokemon.entities.PokemonEntity;
import co.edu.uniandes.csw.pokemon.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.pokemon.persistence.PokemonPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@Stateless
public class PokemonLogic {

        private static final Logger LOGGER = Logger.getLogger(PokemonLogic.class.getName());

    @Inject
    private PokemonPersistence persistence;
    
 
     public PokemonEntity createCalificacion(Long pqrId, PokemonEntity calificacionEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de crear Pokemon");
        if(comprobarObjeto(calificacionEntity) == false)
        {
            throw new BusinessLogicException("Pokemon tiene una caracteristica invalida \"" + calificacionEntity.toString() + "\"");
        }
        LOGGER.log(Level.INFO, "Termina proceso de creación de Pokemon");
        return persistence.create(calificacionEntity);
    }
    
      public boolean comprobarObjeto(PokemonEntity pokemon)
    {
        if(!pokemon.getTipo().equals(pokemon.getDebilidad())){
            if(pokemon.getAtaque()>=10 && pokemon.getAtaque()<=100)
            return true;
        }
            return false;
    }

}
