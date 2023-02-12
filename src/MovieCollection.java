import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MovieCollection {
  private ArrayList<Movie> movies;
  private Scanner scanner;

  public MovieCollection(String fileName) {
    importMovieList(fileName);
    scanner = new Scanner(System.in);
  }

  public ArrayList<Movie> getMovies() {
    return movies;
  }

  public void menu() {
    String menuOption = "";

    System.out.println("Welcome to the movie collection!");
    System.out.println("Total: " + movies.size() + " movies");

    while (!menuOption.equals("q")) {
      System.out.println("------------ Main Menu ----------");
      System.out.println("- search (t)itles");
      System.out.println("- search (k)eywords");
      System.out.println("- search (c)ast");
      System.out.println("- see all movies of a (g)enre");
      System.out.println("- list top 50 (r)ated movies");
      System.out.println("- list top 50 (h)igest revenue movies");
      System.out.println("- (q)uit");
      System.out.print("Enter choice: ");
      menuOption = scanner.nextLine();

      if (menuOption.equals("t")) {
        searchTitles();
      } else if (menuOption.equals("c")) {
        searchCast();
      } else if (menuOption.equals("k")) {
        searchKeywords();
      } else if (menuOption.equals("g")) {
        listGenres();
      } else if (menuOption.equals("r")) {
        listHighestRated();
      } else if (menuOption.equals("h")) {
        listHighestRevenue();
      } else if (menuOption.equals("q")) {
        System.out.println("Goodbye!");
      } else {
        System.out.println("Invalid choice!");
      }
    }
  }

  private void importMovieList(String fileName) {
    try {
      movies = new ArrayList<Movie>();
      FileReader fileReader = new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String line = bufferedReader.readLine();

      while ((line = bufferedReader.readLine()) != null) {
        // get data from the columns in the current row and split into an array
        String[] movieFromCSV = line.split(",");

        /* TASK 1: FINISH THE CODE BELOW */
        // using the movieFromCSV array,
        // obtain the title, cast, director, tagline,
        // keywords, overview, runtime (int), genres,
        // user rating (double), year (int), and revenue (int)
        String title = movieFromCSV[0];
        String cast = movieFromCSV[1];
        String director = movieFromCSV[2];
        String tagline = movieFromCSV[3];
        String keywords = movieFromCSV[4];
        String overview = movieFromCSV[5];
        int runtime = Integer.parseInt(movieFromCSV[6]);
        String genres = movieFromCSV[7];
        double userRating = Double.parseDouble(movieFromCSV[8]);
        int year = Integer.parseInt(movieFromCSV[9]);
        int revenue = Integer.parseInt(movieFromCSV[10]);

        // create a Movie object with the row data:
        Movie nextMovie = new Movie(title, cast, director, tagline, keywords, overview, runtime, genres, userRating, year, revenue);

        // add the Movie to movies:
        movies.add(nextMovie);

      }
      bufferedReader.close();
    } catch(IOException exception) {
      System.out.println("Unable to access " + exception.getMessage());
    }
  }

  private void searchTitles() {
    System.out.print("Enter a title search term: ");
    String searchTerm = scanner.nextLine();

    // prevent case sensitivity
    searchTerm = searchTerm.toLowerCase();

    // arraylist to hold search results
    ArrayList<Movie> results = new ArrayList<Movie>();

    // search through ALL movies in collection
    for (int i = 0; i < movies.size(); i++) {
      String movieTitle = movies.get(i).getTitle();
      movieTitle = movieTitle.toLowerCase();

      if (movieTitle.indexOf(searchTerm) != -1) {
        //add the Movie objest to the results list
        results.add(movies.get(i));
      }
    }

    if (results.size() > 0) {
      // sort the results by title
      sortResults(results);

      // now, display them all to the user
      for (int i = 0; i < results.size(); i++) {
        String title = results.get(i).getTitle();

        // this will print index 0 as choice 1 in the results list; better for user!
        int choiceNum = i + 1;
        System.out.println("" + choiceNum + ". " + title);
      }

      System.out.println("Which movie would you like to learn more about?");
      System.out.print("Enter number: ");
      int choice = scanner.nextInt();
      scanner.nextLine();
      Movie selectedMovie = results.get(choice - 1);
      displayMovieInfo(selectedMovie);
      System.out.println("\n ** Press Enter to Return to Main Menu **");
      scanner.nextLine();
    } else {
      System.out.println("\nNo movie titles match that search term!");
      System.out.println("** Press Enter to Return to Main Menu **");
      scanner.nextLine();
    }
  }

  private void sortResults(ArrayList<Movie> listToSort) {
    for (int j = 1; j < listToSort.size(); j++) {
      Movie temp = listToSort.get(j);
      String tempTitle = temp.getTitle();

      int possibleIndex = j;
      while (possibleIndex > 0 && tempTitle.compareTo(listToSort.get(possibleIndex - 1).getTitle()) < 0) {
        listToSort.set(possibleIndex, listToSort.get(possibleIndex - 1));
        possibleIndex--;
      }
      listToSort.set(possibleIndex, temp);
    }
  }
  
  private void displayMovieInfo(Movie movie) {
    System.out.println();
    System.out.println("Title: " + movie.getTitle());
    System.out.println("Tagline: " + movie.getTagline());
    System.out.println("Runtime: " + movie.getRuntime() + " minutes");
    System.out.println("Year: " + movie.getYear());
    System.out.println("Directed by: " + movie.getDirector());
    System.out.println("Cast: " + movie.getCast());
    System.out.println("Overview: " + movie.getOverview());
    System.out.println("User rating: " + movie.getUserRating());
    System.out.println("Box office revenue: " + movie.getRevenue());
  }

  private void searchKeywords() {
    System.out.print("Enter a keyword search term: ");
    String searchTerm = scanner.nextLine();

    // prevent case sensitivity
    searchTerm = searchTerm.toLowerCase();

    // arraylist to hold search results
    ArrayList<Movie> results = new ArrayList<Movie>();

    // search through ALL movies in collection
    for (int i = 0; i < movies.size(); i++) {
      String movieKeyWord = movies.get(i).getKeywords();
      movieKeyWord = movieKeyWord.toLowerCase();

      if (movieKeyWord.indexOf(searchTerm) != -1) {
        //add the Movie objest to the results list
        results.add(movies.get(i));
      }
    }

    if (results.size() > 0) {
      // sort the results by title
      sortResults(results);

      // now, display them all to the user
      for (int i = 0; i < results.size(); i++) {
        String title = results.get(i).getTitle();

        // this will print index 0 as choice 1 in the results list; better for user!
        int choiceNum = i + 1;
        System.out.println("" + choiceNum + ". " + title);
      }

      System.out.println("Which movie would you like to learn more about?");
      System.out.print("Enter number: ");
      int choice = scanner.nextInt();
      scanner.nextLine();
      Movie selectedMovie = results.get(choice - 1);
      displayMovieInfo(selectedMovie);
      System.out.println("\n ** Press Enter to Return to Main Menu **");
      scanner.nextLine();
    } else {
      System.out.println("\nNo movie titles match that search term!");
      System.out.println("** Press Enter to Return to Main Menu **");
      scanner.nextLine();
    }
  }

  private void searchCast() {
    ArrayList<Movie> results = new ArrayList<Movie>();
    System.out.print("Enter a person to search for (first or last name): ");
    String searchTerm = scanner.nextLine();

    // prevent case sensitivity
    searchTerm = searchTerm.toLowerCase();

    // arraylist to hold search results
    ArrayList<String>  castMember = new ArrayList<String>();

    // search through ALL movies in collection
    for (int i = 0; i < movies.size(); i++) {
      String actors = movies.get(i).getCast();
      String[] actorList = actors.split("\\|");
      for (String actor : actorList) {
        if (actor.toLowerCase().indexOf(searchTerm)!=-1){
          if (castMember.indexOf(actor) == -1) {
            castMember.add(actor);
          }
        }
      }
    }
    Collections.sort(castMember);
    for (int i = 0; i < castMember.size(); i++) {
      String name = castMember.get(i);
      // this will print index 0 as choice 1 in the results list; better for user!
      int choiceNum = i + 1;
      System.out.println("" + choiceNum + ". " + name);
    }
    System.out.println("Which would you like to see all movies for?");
    System.out.print("Enter number: ");
    int choice = scanner.nextInt();
    scanner.nextLine();
    String searchForMoviesWithActor = castMember.get(choice - 1);
    for(int s = 0; s < movies.size(); s++) {
      String cast = movies.get(s).getCast();
      if (cast.indexOf(searchForMoviesWithActor) != -1) {
        results.add(movies.get(s));
      }
    }
    sortResults(results);
    for (int p = 0; p < results.size(); p++) {
      String title = results.get(p).getTitle();
      // this will print index 0 as choice 1 in the results list; better for user!
      int choiceNum = p + 1;
      System.out.println("" + choiceNum + ". " + title);
    }
    System.out.println("Which movie would you like to learn more about");
    System.out.print("Enter number: ");
    int choice2 = scanner.nextInt();
    scanner.nextLine();
    Movie newMovie = results.get(choice2-1);
    displayMovieInfo(newMovie);

    System.out.println("\n ** Press Enter to Return to Main Menu **");
    scanner.nextLine();
  }
  
  private void listGenres() {
    ArrayList<Movie> results = new ArrayList<Movie>();
    ArrayList<String> genres = new ArrayList<String>();
    for (int i = 0; i<movies.size(); i++){
      String genre = movies.get(i).getGenres();
      String[] genreList = genre.split("\\|");
      for(int j = 0; j< genreList.length; j++){
        if(genres.indexOf(genreList[j])==-1){
          genres.add(genreList[j]);
        }
      }
    }
    Collections.sort(genres);
    for(int k=0; k<genres.size();k++){
      String toPrint = k+1 + ". " + genres.get(k);
      System.out.println(toPrint);
    }
    System.out.print("Which would you like to see all movies for? ");
    int num = scanner.nextInt();
    scanner.nextLine();
    String search = genres.get(num-1).toLowerCase();
    int num1= 1;
    for(Movie m: movies){
      String genre = m.getGenres().toLowerCase();
      if(genre.contains(search)){
        results.add(m);
      }
    }
    sortResults(results);
    for (Movie movie: results) {
      System.out.println(num1+ ". " + movie.getTitle());
      num1++;
    }
    System.out.println("Which movie would you like to learn more about?");
    System.out.print("Enter number: ");
    int choice = scanner.nextInt();
    scanner.nextLine();
    Movie selectedMovie = results.get(choice - 1);
    displayMovieInfo(selectedMovie);
  }

  
  private void listHighestRated() {
    ArrayList<Movie> results = new ArrayList<Movie>();
    for (int i = 0; i < 50; i++) {
      Movie temp = new Movie("", "", "", "", "", "", 0, "", 0.0, 0, 25);
      for (Movie movie : movies) {
        if (!results.contains(movie) && movie.getUserRating() > temp.getUserRating()) {
          temp = movie;
        }
      }
      results.add(temp);
    }
    for (int i = 0; i < results.size(); i++) {
      String title = results.get(i).getTitle();
      double rating = results.get(i).getUserRating();

      // this will print index 0 as choice 1 in the results list; better for user!
      int choiceNum = i + 1;
      System.out.println("" + choiceNum + ". " + title + ": " + rating);
    }

    System.out.println("Which movie would you like to learn more about?");
    System.out.print("Enter number: ");
    int choice = scanner.nextInt();
    scanner.nextLine();
    Movie selectedMovie = results.get(choice - 1);
    displayMovieInfo(selectedMovie);
    System.out.println("\n ** Press Enter to Return to Main Menu **");
    scanner.nextLine();

  }
  
  private void listHighestRevenue() {
    ArrayList<Movie> results = new ArrayList<Movie>();
    for (int i = 0; i < 50; i++) {
      Movie temp = new Movie("", "", "", "", "", "", 0, "", 0.0, 0, 25);
      for (Movie movie : movies) {
        if (!results.contains(movie) && movie.getRevenue() > temp.getRevenue()) {
          temp = movie;
        }
      }
      results.add(temp);
    }
    for (int i = 0; i < results.size(); i++) {
      String title = results.get(i).getTitle();
      int revenue = results.get(i).getRevenue();

      // this will print index 0 as choice 1 in the results list; better for user!
      int choiceNum = i + 1;
      System.out.println("" + choiceNum + ". " + title + ": " + revenue);
    }

    System.out.println("Which movie would you like to learn more about?");
    System.out.print("Enter number: ");
    int choice = scanner.nextInt();
    scanner.nextLine();
    Movie selectedMovie = results.get(choice - 1);
    displayMovieInfo(selectedMovie);
    System.out.println("\n ** Press Enter to Return to Main Menu **");
    scanner.nextLine();
  }

}