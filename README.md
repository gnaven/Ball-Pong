The program displays a paddle which can be moved using the right and left key. It displays points
on the top left corner and increases everytime you make the ball reflect with the paddle.On the top
right corner there is a clock which is calibrated to show 60 seconds upon full revolution. The top left 
corner also shows the amount of lives/ chances user has with shapes of small red balls. If the user 
misses to hit the ball up with the paddle, a ball decrease until the last the ball is lost after which the 
game is over. The top left corner also shows the Level number user is in. If the user survives the first 30
seconds/ half revolution of the green timer, the user goes to Level 2 which is displayed on the screen.
For surviving another 60 seconds user reaches level 3, and for another 75 seconds the user reaches 
Level 4. With reaching a new level the user also receives a bonus of 10 points. 
The projectile/ ball movement is programed in a way that the x cordinate increases by one unit always.
Using the velocity formulas, the time is calculated and using that time y co-ordinate is calculated for every
instance. The program contains if else statements which modifies the x and y co-ordinates if it reflects 
the frame and the paddle. 
There is also a stationary retangle which acts like an obstacle. if the ball hits that obstacle it reflects
depending from which side the ball approaches the obstacle.
To make sure the green time displays works with real time. The angle of the ticker is increased to show a
30 second increase everytime the Timer in the program for every 500 millisecond.
The ball also seems to move faster ater a new level to make the game more challenging. The x co-ordinate displacement
of the ball increaments.
