#include <stdlib.h>
#include <unistd.h>
#include <xcb/xcb.h>

int
main()
{
  xcb_connection_t *connection = xcb_connect(NULL, NULL);
  xcb_screen_t *screen = xcb_setup_roots_iterator(xcb_get_setup(connection)).data;

  xcb_gcontext_t foreground = xcb_generate_id(connection);
  uint32_t gc_mask = XCB_GC_FOREGROUND | XCB_GC_GRAPHICS_EXPOSURES;
  uint32_t gc_values[2] = {screen->black_pixel, 0};
  xcb_create_gc(
    connection,
    foreground,
    screen->root,
    gc_mask,
    gc_values
  );

  xcb_drawable_t window = xcb_generate_id(connection);
  uint32_t window_mask = XCB_CW_BACK_PIXEL | XCB_CW_EVENT_MASK;
  uint32_t window_values[2] = {screen->white_pixel, XCB_EVENT_MASK_EXPOSURE};
  xcb_create_window(
    connection,
    XCB_COPY_FROM_PARENT,
    window,
    screen->root,
    0, 0,
    200, 200,
    10,
    XCB_WINDOW_CLASS_INPUT_OUTPUT,
    screen->root_visual,
    window_mask,
    window_values 
  );
  xcb_map_window(connection, window);
  xcb_flush(connection);
  xcb_rectangle_t rectangles[] = {
    {10, 10, 100, 100}
  };
  xcb_generic_event_t *event;
  while ((event = xcb_wait_for_event(connection))) {
    switch (event->response_type & ~0x80) {
      case XCB_EXPOSE:
        xcb_poly_rectangle(connection, window, foreground, 1, rectangles);
        xcb_flush(connection);
        break;
      default:
        break;
    }
    free(event);
  }
  //pause();
  xcb_disconnect(connection);
  return 0;
}
