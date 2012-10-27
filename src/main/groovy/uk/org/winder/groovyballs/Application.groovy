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

import static groovyx.javafx.GroovyFX.start


arenaHeight = 300.0
arenaWidth = 300.0

x = 5.0
y = 5.0

start {
    stage(title: 'GroovyBalls', x: 100, y:100, visible: true) {
        scene(width: arenaWidth, height: arenaHeight, fill: BLACK) {
            r = circle(centerX: bind(this, 'x'), centerY: bind(this, 'y'), radius: 10) { fill GREEN }
        }
    }
    timeline(cycleCount: INDEFINITE) {
        at(1.s) {
            change(this, 'x') to arenaWidth / 4
            change(this, 'y') to arenaHeight / 4
        }
    }.play()
}
