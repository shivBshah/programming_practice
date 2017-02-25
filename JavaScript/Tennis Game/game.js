        var canvas;
        var canvasContext;
        var ballX = 50;
        var ballSpeedX = 10;
        var ballY = 100;
        var ballSpeedY = 4;

        var paddle2Y = 250;
        var paddle1Y = 250;
        const PADDLE_HEIGHT = 100;
        const PADDLE_THICKNESS = 10;

        var player1Score = 0;
        var player2Score = 0;
        const WINNING_SCORE = 3;
        var win = false;

        function handleMouseClick(evt) {
          if (win) {
            player1Score = 0;
            player2Score = 0;
            win = false;

          }
        }

        window.onload = function() {
          canvas = document.getElementById('gamecanvas');
          canvasContext = canvas.getContext('2d');
          var framesPerSecond = 30;
          setInterval(function() {
            moveEverything();
            drawEverything();
          }, 1000 / framesPerSecond);

          canvas.addEventListener('mousemove',
            function(evt) {
              var mousePos = calculateMousePos(evt);
              paddle1Y = mousePos.y - (PADDLE_HEIGHT / 2);
            });

          canvas.addEventListener('mousedown', handleMouseClick);
        }

        function calculateMousePos(evt) {
          var rect = canvas.getBoundingClientRect();
          var root = document.documentElement;
          var mouseX = evt.clientX - rect.left - root.scrollLeft;
          var mouseY = evt.clientY - rect.top - root.scrollTop;
          return {
            x: mouseX,
            y: mouseY
          };
        }

        function ballReset() {
          if (player1Score == WINNING_SCORE || player2Score == WINNING_SCORE) {
            win = true;
          }
          ballSpeedX = -ballSpeedX;
          ballSpeedY = 4;
          ballX = canvas.width / 2;
          ballY = canvas.height / 2;
        }

        function moveRightPaddle() {
          var paddleCenter = paddle2Y + (PADDLE_HEIGHT / 2);
          if (paddleCenter < ballY - 35) {
            paddle2Y += 6;
          } else if (paddleCenter > ballY + 35) {
            paddle2Y -= 6;
          }
        }

        function moveEverything() {
          if (win) {
            return;
          }
          moveRightPaddle()
          ballX += ballSpeedX;
          ballY += ballSpeedY;

          if (ballX >= canvas.width - PADDLE_THICKNESS) {
            if (ballY > paddle2Y && ballY < (paddle2Y + PADDLE_HEIGHT)) {
              ballSpeedX = -ballSpeedX;
              var deltaY = ballY - (paddle2Y + PADDLE_HEIGHT / 2);
              ballSpeedY = deltaY * 0.30;
            } else {
              player1Score += 1; //increase score before reset
              ballReset();
            }
          }

          if (ballX <= PADDLE_THICKNESS) {
            if (ballY > paddle1Y && ballY < paddle1Y + PADDLE_HEIGHT) {
              ballSpeedX = -ballSpeedX;
              var deltaY = ballY - (paddle1Y + PADDLE_HEIGHT / 2);
              ballSpeedY = deltaY * 0.30;
            } else {
              player2Score += 1;
              ballReset();
            }
          }

          if (ballY > canvas.height) {
            ballSpeedY = -ballSpeedY;
          }

          if (ballY < 0) {
            ballSpeedY = -ballSpeedY;
          }
        }

        function drawEverything() {
          if (win) {
            var winStat = '';
            if (player1Score >= WINNING_SCORE) {
              winStat = "Player Won!!!";
            } else {
              winStat = "Computer Won!!!";
            }
            canvasContext.fillStyle = 'white';
            canvasContext.font = '25px Serif';
            canvasContext.fillText(winStat, canvas.width / 2 - 100, canvas.height / 2);
            return;
          }

          colorRect(0, 0, canvas.width, canvas.height, 'black');
          drawNet();
          colorRect(0, paddle1Y, PADDLE_THICKNESS, PADDLE_HEIGHT, 'grey');
          colorRect(canvas.width - PADDLE_THICKNESS, paddle2Y, PADDLE_THICKNESS, PADDLE_HEIGHT, 'grey');
          drawCircle(ballX, ballY, 10, 'green');
          canvasContext.font = '15px Serif';
          canvasContext.fillText('Player', 85, 80);
          canvasContext.fillText(player1Score, 100, 100);
          canvasContext.fillText('Computer', canvas.width - 125, 80);
          canvasContext.fillText(player2Score, canvas.width - 100, 100);
        }

        function colorRect(leftX, topY, width, height, color) {
          canvasContext.fillStyle = color;
          canvasContext.fillRect(leftX, topY, width, height);
        }

        function drawCircle(centerX, centerY, radius, color) {
          canvasContext.beginPath();
          canvasContext.arc(centerX, centerY, radius, 0, Math.PI * 2, true);
          canvasContext.fillStyle = color;
          canvasContext.fill();
        }

        function drawNet() {
          for (var i = 0; i < canvas.height; i += 40) {
            canvasContext.fillStyle = 'white';
            canvasContext.fillRect(canvas.width / 2, i, 2, 20);
          }
        }