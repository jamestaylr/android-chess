Android Chess
=============

Introduction
------------

Chess is fun, classic, and competitive game. This is a port of the game to an Android application utilizing the [Sofia API](https://github.com/web-cat/sofia-graphics).

The game uses the native chess symbols embedded in the Android system font to render the chess board. The board looks something like this:

<table>
  <tr>
    <td>♜</td>
    <td>♞</td> 
    <td>♝</td>
    <td></td>
    <td>♚</td>
    <td>♞</td>
    <td>♝</td>
    <td>♜</td>
  </tr>
  <tr>
    <td>♟</td>
    <td>♟</td> 
    <td></td>
    <td></td>
    <td></td>
    <td>♟</td>
    <td>♟</td>
    <td>♟</td>
  </tr>
  <tr>
    <td></td>
    <td></td> 
    <td></td>
    <td></td>
    <td>♟</td>
    <td></td>
    <td></td>
    <td> </td>
  </tr>
  <tr>
    <td></td>
    <td></td> 
    <td>♟</td>
    <td>♛</td>
    <td>♘</td>
    <td></td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td>♙</td>
    <td></td> 
    <td></td>
    <td>♟</td>
    <td></td>
    <td>♙</td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td></td>
    <td></td> 
    <td></td>
    <td>♙</td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td></td>
    <td>♙</td> 
    <td>♙</td>
    <td></td>
    <td>♙</td>
    <td></td>
    <td>♙</td>
    <td>♙</td>
  </tr>
  <tr>
    <td>♖</td>
    <td></td> 
    <td>♗</td>
    <td>♕</td>
    <td>♔</td>
    <td>♗</td>
    <td></td>
    <td>♖</td>
  </tr>
</table>

The application renders the board to the screen and checks piece movement to ensure pieces move to valid locations on the board. In the future, the AI will hopefully expand to manage more complex actions, like predictive movement, castling, and promotion in a fully autonomous AI.

Testing & Deployment
------------

To run this code, simply download the most recent `sofia.jar` and include it in the Android application build path.

Add the following lines to `project.properties` at the root of the application folder:

    target=android-17
    android.library.reference.1=../Sofia-Support

