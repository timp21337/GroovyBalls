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

final class Coordinate {
    final Integer x
    final Integer y
	
    Coordinate(final Integer x, final Integer y) {
        this.x = x
        this.y = y
    }
	
    @Override boolean equals(final Object other) {
        if (this.is(other)) return true
        if (other == null || !(other instanceof Coordinate)) return false
        def o = (Coordinate)other
        this.x == o.x && this.y == o.y
    }
    
	@Override int hashCode() {
		(x * 33) ^ y
	}
}
