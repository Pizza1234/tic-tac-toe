var gameModule = angular.module('gameModule', []);

gameModule.controller('newGameController', ['$rootScope', '$scope', '$http', '$location',

    function (rootScope, scope, http, location) {

        rootScope.newGameData = null;

        scope.newGameOptions = {
            availablePieces: [
                {name: 'X'},
                {name: 'O'}
            ],
            selectedPiece: {name: 'O'},
            availableGameTypes: [
                {name: 'COMPETITION'},
                {name: 'COMPUTER'}
            ],
            selectedBoardDimension: {name: 'COMPUTER'}
        };

        scope.createNewGame = function () {
            rootScope.newGameData = {piece: this.newGameData.piece};
            location.path('/game');


        }

    }
]);


gameModule.controller('gameController', ['$rootScope', '$scope', '$http', '$location',
    function (rootScope, scope, http, location) {
       getInitialData();

        function getInitialData() {
            scope.rows = [
                [
                    {'id': '00', 'letter': '', 'class': 'box'},
                    {'id': '01', 'letter': '', 'class': 'box'},
                    {'id': '02', 'letter': '', 'class': 'box'}
                ],
                [
                    {'id': '10', 'letter': '', 'class': 'box'},
                    {'id': '11', 'letter': '', 'class': 'box'},
                    {'id': '12', 'letter': '', 'class': 'box'}
                ],
                [
                    {'id': '20', 'letter': '', 'class': 'box'},
                    {'id': '21', 'letter': '', 'class': 'box'},
                    {'id': '22', 'letter': '', 'class': 'box'}
                ]
            ];

            angular.forEach(scope.rows, function (row) {
                row[0].letter = row[1].letter = row[2].letter = '';
                row[0].class = row[1].class = row[2].class = 'box';
            });

            if (rootScope.newGameData.piece === 'X') {
                rootScope.board = {
                    board: [
                        "   ",
                        "   ",
                        "   "
                    ]
                };
            } else {
                rootScope.board = {
                    board: [
                        "   ",
                        " X ",
                        "   "
                    ]
                };
                updateBoard();
            }
        }

        function checkPlayerTurn() {
           var count = rootScope.board.board.toString().split('')
               .map( function (char){
                   if(char === 'O') {
                       return -1;
                   } else if (char === 'X') {
                       return 1;
                   } else {
                       return 0;
                   }
               }).reduce(function(sum, current) {
                   return sum + current;
                }, 0);

           return (count === 0 && rootScope.newGameData.piece === 'X') ||
               (count > 0 && rootScope.newGameData.piece === 'O');
        }



        function checkIfBoardCellAvailable(boardRow, boardColumn) {

            return rootScope.board.board[boardRow][boardColumn] == ' ';
        }

        function replaceAt(str, index, replacement) {
            return str.substr(0, index) + replacement+ str.substr(index + replacement.length);
        }

        function updateBoard() {
            for (i = 0; i < rootScope.board.board.length; i++) {
                for (j = 0; j < rootScope.board.board.length; j++) {
                    scope.rows[i][j].letter = rootScope.board.board[i][j];
                }
            }
        }

        scope.markPlayerMove = function (column) {
            var boardRow = parseInt(column.id.charAt(0));
            var boardColumn = parseInt(column.id.charAt(1));
            var params = {'boardRow': boardRow, 'boardColumn': boardColumn};

            if (checkIfBoardCellAvailable(boardRow, boardColumn)) {
                // if player has a turn
                if (checkPlayerTurn()) {
                    rootScope.board.board[boardRow] = replaceAt(rootScope.board.board[boardRow], boardColumn, rootScope.newGameData.piece);
                    updateBoard();
                    http.post("/board", rootScope.board, {
                        headers: {
                            'Content-Type': 'application/json; charset=UTF-8'
                        }
                    }).then(function (response) {
                        rootScope.board = response.data;
                        updateBoard();
                        setTimeout(function () {
                            if (response.data.status != 'Playing!') {
                                alert(response.data.status);
                                rootScope.$apply(function() {
                                    location.path("/");
                                    console.log(location.path());
                                });
                            }
                        }, 100);
                    }).catch(function (response) {
                        scope.errorMessage = "Can't send the move"
                    });

                }
            }
        };


    }
]);



