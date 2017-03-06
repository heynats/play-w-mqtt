package models

import javax.inject.Inject

import org.specs2.mutable._

class TSDataSpec @Inject() (db: DB) extends Specification {

  "TSData" should {
    "be creatable" in {
      val tsdata = db.save(TSData("MACHINE1", true, 19.6, 42.0))
      tsdata.id must not (beNull)
      tsdata.station must beEqualTo ("MACHINE1")
      tsdata.status must beEqualTo (true)
      tsdata.temp must beEqualTo (19.6)
      tsdata.humid must beEqualTo (42.0)
    }
  }

}