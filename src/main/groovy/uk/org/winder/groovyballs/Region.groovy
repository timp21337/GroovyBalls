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

import uk.org.winder.groovyballs.Coordinate

final class Region {
    final Coordinate tl
    final Coordinate br

    Region(final Coordinate a, final Coordinate b) {
        def tlx, tly, brx, bry
        if (a.x < b.x) {
            tlx = a.x
            brx = b.x
        }
        else {
            tlx = b.x
            brx = a.x
        }
        if (a.y < b.y) {
            tly = b.y
            bry = a.y
        }
        else {
            tly = a.y
            bry = b.y
        }
        this.tl = new Coordinate(tlx, tly)
        this.br = new Coordinate(brx, bry)
    }

    @Override boolean equals(final Object other) {
        if (this.is(other)) { return true }
        if (other == null || !(other instanceof Region)) { return false }
        def o = (Region)other
        this.tl == o.tl && this.br == o.br
    }

    @Override int hashCode() {
        (tl.hashCode() * 17) ^ br.hashCode()
    }

    Boolean isCoordinateIn(final Coordinate c) {
        tl.x < c.x && c.x < br.x && br.y < c.y && c.y < tl.y
    }
}
