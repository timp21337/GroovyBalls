//  GroovyBalls — an example of using GroovyFX.
//
//  Copyright © 2012 Russel Winder
//
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.

package uk.org.winder.groovyballs

import spock.lang.Specification

class Region_Test extends Specification {
    def "zero parameter creation fails"() {
        when: def region = new Region()
        then: thrown(GroovyRuntimeException)
    }

    def "one parameter creation fails"() {
        when: def region = new Region(new Coordinate(10, 20))
        then: thrown(GroovyRuntimeException)
    }

    def "two parameters creation succeeds and fields are readable"() {
        when:
            def tl = new Coordinate(10, 120)
            def br = new Coordinate(110, 20)
            def region = new Region(tl, br)
        then:
            region.tl == tl && region.br == br
    }

    def "three parameters creation fails"() {
        when: def region = new Region(new Coordinate(10, 20), new Coordinate(110, 120), new Coordinate(210, 220))
        then: thrown(GroovyRuntimeException)
    }

    def "change of tl coordinate fails: Regions are value types"() {
        when:
            def a = new Coordinate(10, 20)
            def b = new Coordinate(110, 120)
            def region = new Region(a, b)
            region.a = b
        then:
            thrown(GroovyRuntimeException)
    }

    def "change of br coordinate fails: Regions are value types"() {
        when:
            def a = new Coordinate(10, 20)
            def b = new Coordinate(110, 120)
            def region = new Region(a, b)
            region.b = a
        then:
            thrown(GroovyRuntimeException)
    }

	def "regions can determine if a coordinate is in the covered area"() {
		setup:
			def region = new Region(new Coordinate(10, 20), new Coordinate(30, 40))
		when:
			def coordinate = new Coordinate(20, 30)
		then:
			region.isCoordinateIn(coordinate)
	}

    def "regions can determine if a coordinate is outside the covered area"() {
        setup:
            def region = new Region(new Coordinate(10, 20), new Coordinate(30, 40))
        when:
            def coordinate = new Coordinate(100,100)
        then:
            !region.isCoordinateIn(coordinate)
    }

	def "hash function on same values gives some int"() {
		when:
			def a = new Region(new Coordinate(10, 20), new Coordinate (30, 40))
			def b = new Region(new Coordinate(10, 20), new Coordinate (30, 40))
		then:
			a.hashCode() == b.hashCode()
	}

	def "hash function on different values gives different ints"() {
		when:
			def a = new Region(new Coordinate(10, 20), new Coordinate (30, 40))
			def b = new Region(new Coordinate(10, 20), new Coordinate (10, 40))
		then:
			a.hashCode() != b.hashCode()
	}
}
