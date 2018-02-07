package systems.vostok.taxi.drive.app.dao.entity.geo

import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = 'city')
@Canonical
@EqualsAndHashCode(includes = ['id'])
@ToString(includeNames = true, includeFields = true)
class City {
    @Id
    Long id
    String name
    String state
}