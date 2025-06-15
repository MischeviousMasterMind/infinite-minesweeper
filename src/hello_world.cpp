#include "console.hpp"

#ifdef __EMSCRIPTEN__
#include <emscripten/emscripten.h>
#else
#define EMSCRIPTEN_KEEPALIVE
#endif

extern "C" {

    EMSCRIPTEN_KEEPALIVE void print(int msg) {

        console_log(msg);

    }

}