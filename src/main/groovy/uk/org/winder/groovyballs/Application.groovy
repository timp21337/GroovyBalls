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

import javafx.animation.AnimationTimer

import static groovyx.javafx.GroovyFX.start

arenaHeight = 300.0
arenaWidth = 300.0

balls = [
	green: [glyph: null, data: new BallModel(x: 5.0, y: 5.0, r:10, velocity: 5, angle: 0.2)],
	red: [glyph: null, data: new BallModel(x: 5.0, y: arenaHeight - 5.0, r: 10, velocity: 5, angle: 1.7)],
	]

start {
    stage(title: 'GroovyBalls', x: 100, y:100, visible: true) {
        scene(width: arenaWidth, height: arenaHeight, fill: BLACK) {
            balls.green.glyph = circle(centerX: bind(balls.green.data.x()), centerY: bind(balls.green.data.y()), radius: bind(balls.green.data.r())) { fill GREEN }
            balls.red.glyph = circle(centerX: bind(balls.red.data.x()), centerY: bind(balls.red.data.y()), radius: bind(balls.red.data.r())) { fill RED }
			collisionCheck = { ->
				if (balls.green.data.intersects(balls.red.data)) {
					balls.green.data.randomAngle()
					balls.red.data.randomAngle()
				}
				else {
					balls.green.glyph.fill = GREEN
					balls.red.glyph.fill = RED
				}
			}
        }
    }
	([handle: {collisionCheck()}] as AnimationTimer).start()
    timeline(cycleCount: INDEFINITE, autoReverse: false) {
        at(20.ms, onFinished: {
            balls.green.data.x += balls.green.data.velocity * Math.sin(balls.green.data.angle)
            balls.green.data.y += balls.green.data.velocity * Math.cos(balls.green.data.angle)
            balls.red.data.x += balls.red.data.velocity * Math.sin(balls.red.data.angle)
            balls.red.data.y += balls.red.data.velocity * Math.cos(balls.red.data.angle)
        })
    }.play()
}
