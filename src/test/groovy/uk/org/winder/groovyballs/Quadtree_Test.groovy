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

class Quadtree_Test extends Specification {
    def "zero parameter creation should fail but doesn't, this is an error"() {
        when: def quadtree = new Quadtree()
        then: thrown(GroovyRuntimeException)
    }

    def "one parameter creation fails"() {
        when: def quadtree = new Quadtree(null)
        then: thrown(GroovyRuntimeException)
    }

    def "two parameter creation succeeds"() {
        when: def quadtree = new Quadtree(null, null)
        then: quadtree != null
    }

    def "three parameter creation fails"() {
        when: def quadtree = new Quadtree(null, null, null)
        then: thrown(GroovyRuntimeException)
    }
}
