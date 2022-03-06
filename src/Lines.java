public class Lines extends LineBase{
    public void twoPointForm(int x0, int y0, int x1, int y1, int[][] framebuffer) throws NullPointerException, ArrayIndexOutOfBoundsException {

        if(x0 < x1) {
            //less precise
//            for (int x = x0; x <= x1; x++) {
//                int y = (int) (((double) (y1 - y0) / (double) (x1 - x0)) * (double) (x - x0) + (double) y0);
//                framebuffer[x][y] = 255;
//            }
            // more precise
            for (double x = x0; x <= x1; x= x + 0.01) {
                int y = (int) (((double) (y1 - y0) / (double) (x1 - x0)) * (double) (x - x0) + (double) y0);
                framebuffer[(int)x][y] = 255;
            }
        }
        else
        {
            //less precise
//            for (int x = x1; x <= x0; x++) {
//                int y = (int) (((double) (y1 - y0) / (double) (x1 - x0)) * (double) (x - x0) + (double) y0);
//                framebuffer[x][y] = 255;
//            }
            //more precise
            for (double x = x1; x <= x0; x= x + 0.01) {
                int y = (int) (((double) (y1 - y0) / (double) (x1 - x0)) * (double) (x - x0) + (double) y0);
                framebuffer[(int)x][y] = 255;
            }
        }

    }
    public void parametricForm(int x0, int y0, int x1, int y1, int framebuffer[][]) throws NullPointerException, ArrayIndexOutOfBoundsException{

        for( double t = 0; t < 1.0; t = t + 0.001)
        {
            double x = x0 + (x1-x0)*t;
            double y = y0 + (y1 - y0)*t;
            framebuffer[(int)x][(int)y] = 255;
        }
    }
    //Algorithm pulled from: http://rosettacode.org/wiki/Bitmap/Bresenham%27s_line_algorithm#Java

    public void BresenhamForm(int x0, int y0, int x1, int y1, int framebuffer[][]) throws NullPointerException, ArrayIndexOutOfBoundsException
    {
        int d = 0;

        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);

        int dx2 = 2 * dx; // slope scaling factors to
        int dy2 = 2 * dy; // avoid floating point

        int ix = x0 < x1 ? 1 : -1; // increment direction
        int iy = y0 < y1 ? 1 : -1;

        int x = x0;
        int y = y0;

        if (dx >= dy) {
            while (true) {
                framebuffer[y][x] = 255;
                if (x == x1)
                    break;
                x += ix;
                d += dy2;
                if (d > dx) {
                    y += iy;
                    d -= dx2;
                }
            }
        } else {
            while (true) {
                framebuffer[y][x] = 255;
                if (y == y1)
                    break;
                y += iy;
                d += dx2;
                if (d > dy) {
                    x += ix;
                    d -= dy2;
                }
            }
        }
    }
    public void BresenhamFormRGB(int x0, int y0, int x1, int y1, int framebuffer[][][], RGBColor C0, RGBColor C1) throws NullPointerException, ArrayIndexOutOfBoundsException
    {
        int d = 0;

        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);

        int dx2 = 2 * dx; // slope scaling factors to
        int dy2 = 2 * dy; // avoid floating point

        int ix = x0 < x1 ? 1 : -1; // increment direction
        int iy = y0 < y1 ? 1 : -1;

        int x = x0;
        int y = y0;

        if (dx >= dy) {
            while (true) {
                framebuffer[0][y][x] = (int)(C1.R + (((C1.R - C0.R) / (double)dx) * x));
                framebuffer[1][y][x] = (int)(C1.G + (((C1.G - C0.G) / (double)dx) * x));
                framebuffer[2][y][x] = (int)(C1.B + (((C1.B - C0.B) / (double)dx) * x));
                if (x == x1)
                    break;
                x += ix;
                d += dy2;
                if (d > dx) {
                    y += iy;
                    d -= dx2;
                }
            }
        } else {
            while (true) {
                framebuffer[0][y][x] = (int)(C1.R + (((C1.R - C0.R) / (double)dy) * y));
                framebuffer[1][y][x] = (int)(C1.G + (((C1.G - C0.G) / (double)dy) * y));
                framebuffer[2][y][x] = (int)(C1.B + (((C1.B - C0.B) / (double)dy) * y));
                if (y == y1)
                    break;
                y += iy;
                d += dx2;
                if (d > dy) {
                    x += ix;
                    d -= dy2;
                }
            }
        }
    }
}
