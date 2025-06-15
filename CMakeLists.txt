cmake_minimum_required(VERSION 3.14)
project(mineswept-prealpha)
set (CMAKE_CXX_STANDARD 17)

add_definitions(-std=c++17)

include_directories(include)

add_executable(${PROJECT_NAME})

file(GLOB_RECURSE SOURCE_FILES src/*.cpp)

target_sources(${PROJECT_NAME} PRIVATE ${SOURCE_FILES})

set(EXECUTABLE_OUTPUT_PATH ../docs/scripts)

set(CMAKE_EXECUTABLE_SUFFIX ".wasm")

set_target_properties(${PROJECT_NAME} PROPERTIES COMPILE_FLAGS "-Os -s SIDE_MODULE=1 ")
set_target_properties(${PROJECT_NAME} PROPERTIES LINK_FLAGS    "-Os -s WASM=1 -s SIDE_MODULE=1 -s STANDALONE_WASM --no-entry")