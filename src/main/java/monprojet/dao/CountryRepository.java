package monprojet.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import monprojet.entity.City;
import monprojet.entity.Country;
import monprojet.dao.Projection;

// This will be AUTO IMPLEMENTED by Spring 

public interface CountryRepository extends JpaRepository<Country, Integer> {
    @Query(value="SELECT SUM(City.population) as nbPop"
            +"FROM Country"
            +"Inner join City On Country.id=City.country_id"
            +"WHERE City.country_id = :codePays", nativeQuery = true)
    public Integer idPopulation (Integer codePays);

    @Query(value="SELECT Country.name, Sum(City.population)"
            +"FROM Country"
            +"INNER JOIN City ON Country.id=City.country_id"
            + "Group by Country.name", nativeQuery = true)
    public List<Projection> popParPays();
}
