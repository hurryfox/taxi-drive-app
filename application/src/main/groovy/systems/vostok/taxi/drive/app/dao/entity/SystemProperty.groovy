package systems.vostok.taxi.drive.app.dao.entity

import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import systems.vostok.taxi.drive.app.dao.ObjectCreator

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = 'system_properties')
@Canonical
@EqualsAndHashCode(includes = ['property'])
@ToString(includeNames = true, includeFields = true)
class SystemProperty implements ObjectCreator {
    @Id
    String property
    String value

    static interface Constants {
        String PROPERTY_GEO_VERSION = 'geo_version'
    }
}
