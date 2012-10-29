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

radius = 15.0
offset = radius / 2.0 + radius

ballsInitializationData = [
    green: [x: offset, y: offset, r: radius, dx: 1.0, dy: 0.8],
    red: [x: offset, y: arenaHeight - offset, r: radius, dx: 0.8, dy: -1.0],
    yellow: [x: offset + 0.5 * arenaWidth, y: 0.5 * arenaHeight, r: 2 * radius, dx: 0, dy: -0.5],
]

elasticCollision = {a, b ->
    // TODO: This is not correct it's just here to see what happens.
    a.dx = -a.dx
    a.dy = -a.dy
    b.dx = -b.dx
    b.dy = -b.dy
}

collisionCheck = { ->
    balls.each { key, value ->
        def x = value.data.x + 0.5 * value.data.dx
        def y = value.data.y + 0.5 * value.data.dy
        def r = value.data.r
        if (x + r >= arenaWidth || x - r <= 0) { value.data.dx = -value.data.dx }
        if (y + r >= arenaHeight || y - r <= 0) { value.data.dy = -value.data.dy }
    }
    if (balls.green.data.intersects(balls.red.data)) {
        elasticCollision(balls.green.data, balls.red.data)
    }
    balls.each { key, value ->
        value.data.x += value.data.dx
        value.data.y += value.data.dy
    }
}

start {
    stage(title: 'GroovyBalls', x: 100, y:100, visible: true) {
        scene(width: arenaWidth, height: arenaHeight, fill: BLACK) {
            balls = [:]
            ballsInitializationData.each {key, value -> balls[key] = [glyph: null, data: new BallModel(value)]}
            ballsInitializationData.each {key, value ->
                def datum = balls[key].data
                balls[key].glyph = circle(centerX: bind(datum.x()), centerY: bind(datum.y()), radius: bind(datum.r())) { fill key }
            }
        }
    }
    ([handle: {collisionCheck()}] as AnimationTimer).start()
    timeline(cycleCount: INDEFINITE, autoReverse: false).play()
}
