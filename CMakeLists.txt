cmake_minimum_required(VERSION 3.22)
project(jeuEsir)

set(CMAKE_CXX_STANDARD 14)
INCLUDE(FindPkgConfig)
find_package(SDL2 REQUIRED)

set(CMAKE_MODULE_PATH ${CMAKE_MODULE_PATH} "${CMAKE_SOURCE_DIR}/cmake/")

add_executable(jeuEsir src/jeuESIR.cpp src/entity/Entity.cpp
        src/entity/Entity.h
        src/entity/GameObject.cpp
        src/entity/GameObject.h
        src/entity/Hero.cpp
        src/entity/Hero.h src/game/Game.cpp
        src/game/Game.h
        src/game/Map.cpp
        src/game/Map.h src/graphics/Renderer.cpp
        src/graphics/Renderer.h
        src/graphics/TextureManager.cpp
        src/graphics/TextureManager.h src/utils/CSVReader.cpp
        src/utils/CSVReader.h
        src/utils/MathUtils.h
        src/utils/Rectangle.h
        src/utils/Timer.cpp
        src/utils/Timer.h
        src/utils/Vector2.h
        src/constantes.h)

PKG_SEARCH_MODULE(SDL2 REQUIRED sdl2)
find_package(SDL2_image REQUIRED)

include_directories(${SDL2_INCLUDE_DIRS} ${SDL_IMAGE_INCLUDE_DIRS})
target_link_libraries (jeuEsir PRIVATE ${SDL2_LIBRARIES} ${SDL2_GFX_LIBRARIES} ${SDL2_TTF_LIBRARIES} ${SDL_IMAGE_LIBRARIES})