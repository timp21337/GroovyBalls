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

class Coordinate_Test extends Specification {
    def "zero parameter creation fails"() {
        when: def coordinate = new Coordinate()
        then: thrown(GroovyRuntimeException)
    }

    def "one parameter creation fails"() {
         when: def coordinate = new Coordinate(10)
         then: thrown(GroovyRuntimeException)
     }

    def "two parameters creation succeeds and fields are readable"() {
        when: def coordinate = new Coordinate(10, 20)
        then:  coordinate.x == 10 && coordinate.y == 20
    }

    def "three parameters creation fails"() {
         when: def coordinate = new Coordinate(10, 20, 30)
         then: thrown(GroovyRuntimeException)
     }

    def "named parameter creation fails"() {
        when: def coordinate = new Coordinate(x:10, y:20)
        then: thrown(GroovyRuntimeException)
    }

    def "change of x coordinate fails: Coordinates are value types"() {
        when:
            def coordinate = new Coordinate(10, 20)
            coordinate.x = 30
        then:
            thrown(GroovyRuntimeException)
    }

    def "change of y coordinate fails: Coordinates are value types"() {
        when:
            def coordinate = new Coordinate(10, 20)
            coordinate.y = 30
        then:
            thrown(GroovyRuntimeException)
    }

	def "hash function on same values gives some int"() {
		when:
			def a = new Coordinate(10, 20)
			def b = new Coordinate(10, 20)
		then:
			a.hashCode() == b.hashCode()
	}
	
	def "hash function on different values gives different ints"() {
		when:
			def a = new Coordinate(10, 20)
			def b = new Coordinate (10, 40)
		then:
			a.hashCode() != b.hashCode()
	}
}
