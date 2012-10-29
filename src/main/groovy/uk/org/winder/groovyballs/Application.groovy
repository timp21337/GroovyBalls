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
arenaWidth = 400.0

radius = 10.0
offset = radius / 2.0 + 10.0

balls = [
	green: [glyph: null, data: new BallModel(x: offset, y: offset, r: radius, dx: 1.0, dy: 0.8)],
	red: [glyph: null, data: new BallModel(x: offset, y: arenaHeight - offset, r: radius, dx: 0.8, dy: -1.0)],
	]

start {
    stage(title: 'GroovyBalls', x: 100, y:100, visible: true) {
        scene(width: arenaWidth, height: arenaHeight, fill: BLACK) {
            balls.green.glyph = circle(centerX: bind(balls.green.data.x()), centerY: bind(balls.green.data.y()), radius: bind(balls.green.data.r())) { fill GREEN }
            balls.red.glyph = circle(centerX: bind(balls.red.data.x()), centerY: bind(balls.red.data.y()), radius: bind(balls.red.data.r())) { fill RED }
                collisionCheck = { ->
                    balls.each { key, value ->
                        if (value.data.x + value.data.r >= arenaWidth || value.data.x - value.data.r <= 0) { value.data.dx = -value.data.dx }
                        value.data.x += value.data.dx
                        if (value.data.y + value.data.r >= arenaHeight || value.data.y - value.data.r <= 0) { value.data.dy = -value.data.dy }
                        value.data.y += value.data.dy
                    }
                    if (balls.green.data.intersects(balls.red.data)) {
                        balls.green.data.randomAngle()
                        balls.red.data.randomAngle()
                    }
                }
        }
    }
    ([handle: {collisionCheck()}] as AnimationTimer).start()
    timeline(cycleCount: INDEFINITE, autoReverse: false).play()
}
