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

import groovyx.javafx.beans.FXBindable

@FXBindable final class BallModel {
	Double x
	Double y
	Double r
	Double dx
	Double dy
	boolean intersects(final BallModel other) {
        def xSep = x - other.x
        def ySep = y - other.y
        def sep = r + other.r
        xSep * xSep + ySep * ySep < sep * sep
	}
}
