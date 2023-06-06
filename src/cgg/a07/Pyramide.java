package cgg.a07;

import cgtools.*;



    public record Pyramide(Point point, double height, double baseLength, Material material) implements Shape {
        public Hit intersect(Ray ray) {
            // Vektoren für die Seiten der Pyramide
            Direction baseVector1 = new Direction(baseLength / 2, 0, baseLength / 2);
            Direction baseVector2 = new Direction(-baseLength / 2, 0, baseLength / 2);
            Direction baseVector3 = new Direction(-baseLength / 2, 0, -baseLength / 2);
            Direction baseVector4 = new Direction(baseLength / 2, 0, -baseLength / 2);
            Direction apexVector = new Direction(0, height, 0);
    
            // Seitenflächen der Pyramide
            Shape triangle1 = new Triangle(
                    Vector.add(point, baseVector1),
                    Vector.add(point, baseVector2),
                    Vector.add(point, apexVector),
                    material
            );
            Shape triangle2 = new Triangle(
                    Vector.add(point, baseVector2),
                    Vector.add(point, baseVector3),
                    Vector.add(point, apexVector),
                    material
            );
            Shape triangle3 = new Triangle(
                    Vector.add(point, baseVector3),
                    Vector.add(point, baseVector4),
                    Vector.add(point, apexVector),
                    material
            );
            Shape triangle4 = new Triangle(
                    Vector.add(point, baseVector4),
                    Vector.add(point, baseVector1),
                    Vector.add(point, apexVector),
                    material
            );
    
            // Überprüfung der Schnitte mit den Seitenflächen
            Hit hit = triangle1.intersect(ray);
            if (hit != null && ray.isValid(hit.t()))
                return hit;
    
            hit = triangle2.intersect(ray);
            if (hit != null && ray.isValid(hit.t()))
                return hit;
    
            hit = triangle3.intersect(ray);
            if (hit != null && ray.isValid(hit.t()))
                return hit;
    
            hit = triangle4.intersect(ray);
            if (hit != null && ray.isValid(hit.t()))
                return hit;
    
            return null; // Kein Schnittpunkt gefunden
        }
    }
    

