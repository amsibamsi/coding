int has_verbose_option(int argc, char **argv) {
  for (int i = 1; i < argc; ++i) {
    if ((argv[i][0] == '-') && (argv[i][1] == 'v')) return 1;
  }
  return 0;
}
