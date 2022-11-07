import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class XoGame implements BotFunctions {
    /* XO Commands :
    /newGame : starts a new game and generates an invitation code
    /joinGame : if there was a game with invitation code in wittingList then it would start
    /getTurn : show who's turn is it in the game
    /mark : if it would be the player's turn then he can mark the cell
    /help : send command tutorial
     */
    private final HashMap<Integer, Integer> waitingList;
    private final ArrayList<Game> allGames;

    public XoGame() {
        waitingList = new HashMap<>();
        allGames = new ArrayList<>();
    }

    @Override
    public String[] handleRequest(String command, int senderId , int groupId) {
        if (command.startsWith("/newGame")) {
            return handleNewGameCommand(command, senderId);
        } else if (command.startsWith("/joinGame")) {
            return handleJoinGameCommand(command, senderId);
        } else if (command.startsWith("/getTurn")) {
            return handleGetTurnCommand(command, senderId);
        } else if (command.startsWith("/mark")) {
            return handleMarkCommand(command, senderId);
        } else if (command.startsWith("/help")) {
            return handleHelpCommand(command, senderId);
        }
        return new String[]{"0", "0", "0"};
    }

    private String[] handleNewGameCommand(String command, int senderId) {
        boolean isFound = false;
        for (Game game : allGames) {
            if (game.player1id == senderId || game.player2id == senderId) {
                isFound = true;
                break;
            }
        }
        if (!waitingList.containsValue(senderId) && !isFound) {
            Random random = new Random();
            int invitationToken;
            do {
                invitationToken = random.nextInt(Integer.MAX_VALUE);
            } while (waitingList.containsKey(invitationToken));
            waitingList.put(invitationToken, senderId);
            String text = "Your invitation code is \n" + invitationToken;
            return new String[]{"1", String.valueOf(senderId), text};
        }
        return new String[]{"1", String.valueOf(senderId), "there is a game in progress ..."};
    }

    private String[] handleJoinGameCommand(String command, int senderId) {
        try {
            String invitationCode = command.substring(10);
            System.out.println(invitationCode);
            int code = Integer.parseInt(invitationCode);
            if (waitingList.containsKey(code)) {
                GameState gameState = new GameState(waitingList.get(code), senderId);
                allGames.add(new Game(gameState, waitingList.get(code), senderId));
                String receivers = senderId + " " + waitingList.get(code);
                String text = "the game started \n" +
                        gameState.drawGameBoard();
                waitingList.remove(code);
                return new String[]{"1", receivers, text};
            }
        } catch (Throwable throwable) {
            return new String[]{"0", "0", "0"};
        }
        return new String[]{"0", "0", "0"};
    }

    private String[] handleGetTurnCommand(String command, int senderId) {
        GameState gameState = null;
        boolean isFound = false;
        for (Game game:allGames) {
            if (game.player1id == senderId || game.player2id == senderId){
                isFound = true;
                gameState = game.getGameState();
                break;
            }
        }
        if (isFound) {
            int playerNumber;
            if (gameState.getPlayer1Id() == senderId) {
                playerNumber = 1;
            } else {
                playerNumber = 2;
            }
            String text;
            if (gameState.getTurn() == playerNumber) {
                text = "Your turn";
            } else {
                text = "Opponent's turn";
            }
            return new String[]{"1", String.valueOf(senderId), text};
        }
        return new String[]{"0", "0", "0"};
    }

    private String[] handleMarkCommand(String command, int senderId) {
        boolean isFound = false;
        GameState gameState = null;
        Game game = null;
        for (Game game1:allGames) {
            if (game1.player1id == senderId || game1.player2id == senderId){
                isFound = true;
                gameState = game1.gameState;
                game = game1;
                break;
            }
        }
        if (isFound) {
            try {
                int index = Integer.parseInt(command.substring(6)) - 1;
                gameState.markCell(index, senderId);
                String text = gameState.getState() + "\n" + gameState.drawGameBoard();
                int player1Id = gameState.player1Id;
                int player2Id = gameState.player2Id;
                if (gameState.state != 0) {
                    allGames.remove(game);
                }
                String receivers = player1Id + " " + player2Id;
                return new String[]{"1", receivers, text};
            } catch (Throwable throwable) {
                return new String[]{"1", String.valueOf(senderId), "invalid command"};
            }
        }
        return new String[]{"0", "0", "0"};
    }

    private String[] handleHelpCommand(String command, int senderId) {
        String text = "/newGame : starts a new game and generates an invitation code \n" +
                "/joinGame : if there was a game with invitation code in wittingList then it would start \n" +
                "/getTurn : show who's turn is it in the game \n" +
                "/mark : if it would be the player's turn then he can mark the cell \n" +
                "/help : send command tutorial";
        return new String[]{"1", String.valueOf(senderId), text};
    }

    private static class Game {
        private final GameState gameState;
        private final int player1id;
        private final int player2id;

        private Game(GameState gameState, int player1id, int player2id) {
            this.gameState = gameState;
            this.player1id = player1id;
            this.player2id = player2id;
        }

        public GameState getGameState() {
            return gameState;
        }

        public int getPlayer1id() {
            return player1id;
        }

        public int getPlayer2id() {
            return player2id;
        }
    }

    private static class GameState {
        private final int[] cells; // 0 means * , 1 means x , 2 means o
        private int state;// 0 means in progress , 1 means player1 won , 2 means player2 won , 3 means draw
        private int turn;
        private final int player1Id, player2Id;

        GameState(int player1Id, int player2Id) {
            this.cells = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
            this.turn = 1;
            this.state = 0;
            this.player1Id = player1Id;
            this.player2Id = player2Id;
        }

        public int getPlayer2Id() {
            return player2Id;
        }

        public int getPlayer1Id() {
            return player1Id;
        }

        public int getTurn() {
            return turn;
        }

        public int[] getCells() {
            return cells;
        }

        public String getState() {
            switch (state) {
                case 0:
                    return "match in progress";
                case 1:
                    return "player 1 won";
                case 2:
                    return "player 2 won";
                case 3:
                    return "draw";
                default:
                    return ".";
            }
        }

        public void markCell(int index, int requested) {
            int playerNum;
            if (player1Id == requested) {
                playerNum = 1;
            } else {
                playerNum = 2;
            }
            if (cells[index] == 0 && turn % 2 == playerNum % 2) {
                cells[index] = playerNum;
                turn++;
                if (cells[0] == cells[1] && cells[0] == cells[2] && cells[0] != 0) state = playerNum;
                else if (cells[3] == cells[4] && cells[3] == cells[5] && cells[3] != 0) state = playerNum;
                else if (cells[6] == cells[7] && cells[6] == cells[8] && cells[6] != 0) state = playerNum;
                else if (cells[0] == cells[3] && cells[0] == cells[6] && cells[0] != 0) state = playerNum;
                else if (cells[1] == cells[4] && cells[1] == cells[7] && cells[1] != 0) state = playerNum;
                else if (cells[2] == cells[5] && cells[2] == cells[8] && cells[2] != 0) state = playerNum;
                else if (cells[0] == cells[4] && cells[0] == cells[8] && cells[0] != 0) state = playerNum;
                else if (cells[2] == cells[4] && cells[2] == cells[6] && cells[2] != 0) state = playerNum;
            }
            boolean draw = true;
            for (int cell : cells) {
                if (cell == 0) {
                    draw = false;
                    break;
                }
            }
            if (draw) {
                state = 3;
            }
        }

        private String drawGameBoard() {
            char c0 = convertToChar(cells[0]);
            char c1 = convertToChar(cells[1]);
            char c2 = convertToChar(cells[2]);
            char c3 = convertToChar(cells[3]);
            char c4 = convertToChar(cells[4]);
            char c5 = convertToChar(cells[5]);
            char c6 = convertToChar(cells[6]);
            char c7 = convertToChar(cells[7]);
            char c8 = convertToChar(cells[8]);
            return    c0 + "  " + c1 + "  " + c2 + "\n"
                    + c3 + "  " + c4 + "  " + c5 + "\n"
                    + c6 + "  " + c7 + "  " + c8;
        }

        private char convertToChar(int code) {
            switch (code) {
                case 0:
                    return '*';
                case 1:
                    return 'x';
                case 2:
                    return 'o';
                default:
                    return '.';
            }
        }
    }
}
