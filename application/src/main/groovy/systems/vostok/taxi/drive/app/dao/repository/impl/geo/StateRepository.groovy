package systems.vostok.taxi.drive.app.dao.repository.impl.geo

import org.springframework.stereotype.Repository
import systems.vostok.taxi.drive.app.dao.entity.geo.State
import systems.vostok.taxi.drive.app.dao.repository.BasicRepository

import static systems.vostok.taxi.drive.app.util.constant.SqlEntity.STATE

@Repository
interface StateRepository extends BasicRepository<State, String> {
    String entityType = STATE
}
