#ifdef __EMSCRIPTEN__
#include <emscripten/emscripten.h>
#else
#define EMSCRIPTEN_KEEPALIVE
#endif

extern "C"
{
    struct Position2D
    {
        int x;
        int y;

        Position2D(int x, int y) : x(x), y(y) {};

        void set_x(int x)
        {
            this->x = x;
        }

        int get_x()
        {
            return x;
        }

        void set_y(int y)
        {
            this->y = y;
        }

        int get_y()
        {
            return y;
        }

        Position2D* add(Position2D* delta_s)
        {
            x += delta_s->x;
            y += delta_s->y;

            return this;
        }

        Position2D* subtract(Position2D* delta_s)
        {
            x -= delta_s->x;
            y -= delta_s->y;

            return this;    
        }
    };

    class Map
    {
    private:
        int chunk_width;
        int chunk_height;
    };

    class Chunk
    {
    };

    class Tile
    {
    };
}