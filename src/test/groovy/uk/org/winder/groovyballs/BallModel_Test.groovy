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

class BallModel_Test extends Specification {
	def 'two balls not close do not intersect'() {
		when:
			def a = new BallModel(x: 10, y: 10, r: 10)
			def b = new BallModel(x: 110, y: 110, r: 10)
		then:
			!a.intersects(b)
	}
	
	def 'two balls close do intersect'() {
		when:
			def a = new BallModel(x: 10, y: 10, r: 10)
			def b = new BallModel(x: 15, y: 15, r: 10)
		then:
			a.intersects(b)
	}
}
