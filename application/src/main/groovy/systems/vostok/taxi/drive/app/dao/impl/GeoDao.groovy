package systems.vostok.taxi.drive.app.dao.impl

import com.datastax.driver.core.querybuilder.QueryBuilder
import com.datastax.driver.core.querybuilder.Select
import systems.vostok.taxi.drive.app.dao.UniversalDao
import systems.vostok.taxi.drive.app.dao.entity.geo.City
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import systems.vostok.taxi.drive.app.dao.entity.geo.District
import systems.vostok.taxi.drive.app.dao.entity.geo.State
import systems.vostok.taxi.drive.app.dao.entity.geo.Street
import systems.vostok.taxi.drive.app.util.WordUtil

@Component
class GeoDao {
    @Autowired
    @Delegate
    UniversalDao universalDao

    @Autowired
    WordUtil wordUtil

    List<State> getAllStates() {
        Select select = QueryBuilder.select().from('state')
        selectAll(select, State.class)
    }

    List<City> getAllCities() {
        Select select = QueryBuilder.select().from('city')
        selectAll(select, City.class)
    }

    List<Street> getAllStreets() {
        Select select = QueryBuilder.select().from('street')
        selectAll(select, Street.class)
    }

    List<District> getAllDistricts() {
        Select select = QueryBuilder.select().from('district')
        selectAll(select, District.class)
    }

    Street getStreetByNameAndCity(String streetName, String city) {
        Select select = QueryBuilder.select().from('street')
        select.where(QueryBuilder.eq('name', streetName))
                .and(QueryBuilder.eq('city', city))
        select.allowFiltering()

        selectSingle(select, Street.class)
    }

    City getCityByName(String cityName) {
        Select select = QueryBuilder.select().from('city')
        select.where(QueryBuilder.eq('name', cityName))

        selectSingle(select, City.class)
    }

    void addState(State state) {
        insertSingle(state)
    }

    void addCity(City city) {
        insertSingle(city)
    }

    void addStreet(Street street) {
        insertSingle(street)
    }

    void addDistrict(District district) {
        insertSingle(district)
    }

    void deleteState(State state) {
        deleteSingle(State.class, state.id)
    }

    void deleteCity(City city) {
        deleteSingle(City.class, city.id)
    }

    void deleteStreet(Street street) {
        deleteSingle(Street.class, street.id)
    }

    void deleteDistrict(District district) {
        deleteSingle(District.class, district.id)
    }
}