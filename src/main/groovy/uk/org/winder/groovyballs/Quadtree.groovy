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

final class Quadtree {
    private static class Node {
        private final Integer level
        private final Region region
        Node(final Integer l, final Region r) {
            level = l
            region = r
        }
    }
    private final class LeafNode {
        private ArrayList items = new ArrayList()
        LeafNode(final Integer l, final Region r) {
            super(l, r)
        }
    }
    private final class TreeNode {
        private final Node[] children = new Node[4]
        TreeNode(final Integer l, final Region r) {
            super(l, r)
        }
    }

    private Node root
    private final Integer perQuadCount
    Quadtree(final Region r, final Integer c) {
        root = new LeafNode(0, r)
        perQuadCount = c
    }
    void clean() { root = null }
    boolean add(final Coordinate c) {
        if (root instanceof LeafNode) {
            root.items.add(c)
            if (root.items.size() < perQuadCount) {

            }
        }
    }
}
