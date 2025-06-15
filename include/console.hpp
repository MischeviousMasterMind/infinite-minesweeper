#pragma once
#ifdef __EMSCRIPTEN__
#include <emscripten/emscripten.h>
#else
#define EMSCRIPTEN_KEEPALIVE
#endif

extern "C" {
    EMSCRIPTEN_KEEPALIVE void console_log(int message);
}