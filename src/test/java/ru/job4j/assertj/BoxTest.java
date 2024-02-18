package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import ru.job4j.assertj.Box;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube");
    }

    @Test
    void isThisUnknownObject() {
        Box box = new Box(1, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void numberOfSphereIsZero() {
        Box box = new Box(0, 10);
        int number = box.getNumberOfVertices();
        assertThat(number).isEqualTo(0);
    }

    @Test
    void numberOfTetrahedronIsFour() {
        Box box = new Box(4, 10);
        int number = box.getNumberOfVertices();
        assertThat(number).isEqualTo(4);
    }

    @Test
    void numberOfCubeIsEight() {
        Box box = new Box(8, 10);
        int number = box.getNumberOfVertices();
        assertThat(number).isEqualTo(8);
    }

    @Test
    void numberOfUnknownObjectsIsMinusOne() {
        Box box = new Box(12, 10);
        int number = box.getNumberOfVertices();
        assertThat(number).isEqualTo(-1);
    }

    @Test
    void sphereExists() {
        Box box = new Box(0, 10);
        assertThat(box.isExist()).isTrue();
    }

    @Test
    void tetrahedronExists() {
        Box box = new Box(4, 10);
        assertThat(box.isExist()).isTrue();
    }

    @Test
    void cubeExists() {
        Box box = new Box(8, 10);
        assertThat(box.isExist()).isTrue();
    }

    @Test
    void unknownObjectExists() {
        Box box = new Box(3, 10);
        assertThat(box.isExist()).isFalse();
    }

    @Test
    void spheresArea() {
        Box box = new Box(0, 10);
        double result = box.getArea();
        assertThat(result).isCloseTo(1256.64d, withPrecision(0.003d));
    }

    @Test
    void tetrahedronsArea() {
        Box box = new Box(4, 10);
        double result = box.getArea();
        assertThat(result).isCloseTo(173.21d, withPrecision(0.005d));
    }

    @Test
    void cubesArea() {
        Box box = new Box(8, 10);
        double result = box.getArea();
        assertThat(result).isEqualTo(600.0d);
    }

    @Test
    void unknownObjectsArea() {
        Box box = new Box(3, 10);
        double result = box.getArea();
        assertThat(result).isEqualTo(0);
    }

}