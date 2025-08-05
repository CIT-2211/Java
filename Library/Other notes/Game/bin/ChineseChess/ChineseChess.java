import java.util.Scanner;

public class ChineseChess {
    // 棋盘尺寸
    private static final int ROWS = 10;
    private static final int COLS = 9;
    
    // 棋子常量
    private static final char EMPTY = '＋';
    private static final char[] RED_PIECES = {
        '车', '马', '相', '仕', '帅', '仕', '相', '马', '车',
        '　', '炮', '　', '　', '　', '　', '　', '炮', '　',
        '兵', '　', '兵', '　', '兵', '　', '兵', '　', '兵'
    };
    
    private static final char[] BLACK_PIECES = {
        '车', '马', '象', '士', '将', '士', '象', '马', '车',
        '　', '炮', '　', '　', '　', '　', '　', '炮', '　',
        '卒', '　', '卒', '　', '卒', '　', '卒', '　', '卒'
    };
    
    // 棋盘数据
    private char[][] board;
    // 当前玩家 (true: 红方, false: 黑方)
    private boolean currentPlayer;
    // 游戏状态
    private boolean gameOver;
    
    public ChineseChess() {
        initializeBoard();
        currentPlayer = true; // 红方先行
        gameOver = false;
    }
    
    // 初始化棋盘
    private void initializeBoard() {
        board = new char[ROWS][COLS];
        
        // 初始化空棋盘
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                board[i][j] = EMPTY;
            }
        }
        
        // 放置红方棋子
        for (int i = 7; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                int index = (i - 7) * COLS + j;
                if (index < RED_PIECES.length) {
                    board[i][j] = RED_PIECES[index];
                }
            }
        }
        
        // 放置黑方棋子
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < COLS; j++) {
                int index = i * COLS + j;
                if (index < BLACK_PIECES.length) {
                    board[i][j] = BLACK_PIECES[index];
                }
            }
        }
    }
    
    // 打印棋盘
    public void printBoard() {
        System.out.println("\n  0 1 2 3 4 5 6 7 8");
        for (int i = 0; i < ROWS; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < COLS; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("当前玩家: " + (currentPlayer ? "红方" : "黑方"));
    }
    
    // 移动棋子
    public boolean move(int fromRow, int fromCol, int toRow, int toCol) {
        // 检查是否在棋盘范围内
        if (!isValidPosition(fromRow, fromCol) || !isValidPosition(toRow, toCol)) {
            return false;
        }
        
        char piece = board[fromRow][fromCol];
        
        // 检查是否选择了棋子
        if (piece == EMPTY) {
            return false;
        }
        
        // 检查是否移动自己的棋子
        boolean isRedPiece = isRedPiece(piece);
        if (isRedPiece != currentPlayer) {
            return false;
        }
        
        // 检查目标位置是否有己方棋子
        char targetPiece = board[toRow][toCol];
        if (targetPiece != EMPTY && isRedPiece(targetPiece) == currentPlayer) {
            return false;
        }
        
        // 验证走棋规则
        if (!isValidMove(piece, fromRow, fromCol, toRow, toCol)) {
            return false;
        }
        
        // 执行移动
        board[toRow][toCol] = piece;
        board[fromRow][fromCol] = EMPTY;
        
        // 检查是否将军
        if (targetPiece == '将' || targetPiece == '帅') {
            gameOver = true;
            System.out.println("\n游戏结束! " + (currentPlayer ? "红方" : "黑方") + "获胜!");
        }
        
        // 切换玩家
        currentPlayer = !currentPlayer;
        return true;
    }
    
    // 验证走棋规则
    private boolean isValidMove(char piece, int fromRow, int fromCol, int toRow, int toCol) {
        int rowDiff = Math.abs(toRow - fromRow);
        int colDiff = Math.abs(toCol - fromCol);
        
        switch (piece) {
            case '车': case '车':
                return isValidRookMove(fromRow, fromCol, toRow, toCol);
                
            case '马': case '马':
                return isValidKnightMove(fromRow, fromCol, toRow, toCol);
                
            case '炮': case '炮':
                return isValidCannonMove(fromRow, fromCol, toRow, toCol);
                
            case '相': case '象':
                return isValidElephantMove(fromRow, fromCol, toRow, toCol);
                
            case '仕': case '士':
                return isValidAdvisorMove(fromRow, fromCol, toRow, toCol);
                
            case '帅': case '将':
                return isValidKingMove(fromRow, fromCol, toRow, toCol);
                
            case '兵': case '卒':
                return isValidPawnMove(fromRow, fromCol, toRow, toCol, piece == '兵');
                
            default:
                return false;
        }
    }
    
    // 车移动规则
    private boolean isValidRookMove(int fromRow, int fromCol, int toRow, int toCol) {
        // 必须直线移动
        if (fromRow != toRow && fromCol != toCol) {
            return false;
        }
        
        // 检查路径是否畅通
        if (fromRow == toRow) {
            int minCol = Math.min(fromCol, toCol);
            int maxCol = Math.max(fromCol, toCol);
            for (int col = minCol + 1; col < maxCol; col++) {
                if (board[fromRow][col] != EMPTY) {
                    return false;
                }
            }
        } else {
            int minRow = Math.min(fromRow, toRow);
            int maxRow = Math.max(fromRow, toRow);
            for (int row = minRow + 1; row < maxRow; row++) {
                if (board[row][fromCol] != EMPTY) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    // 马移动规则
    private boolean isValidKnightMove(int fromRow, int fromCol, int toRow, int toCol) {
        int rowDiff = Math.abs(toRow - fromRow);
        int colDiff = Math.abs(toCol - fromCol);
        
        // 马走日
        if (!((rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2))) {
            return false;
        }
        
        // 检查马腿
        if (rowDiff == 2) {
            int blockRow = fromRow + (toRow > fromRow ? 1 : -1);
            if (board[blockRow][fromCol] != EMPTY) {
                return false;
            }
        } else {
            int blockCol = fromCol + (toCol > fromCol ? 1 : -1);
            if (board[fromRow][blockCol] != EMPTY) {
                return false;
            }
        }
        
        return true;
    }
    
    // 炮移动规则
    private boolean isValidCannonMove(int fromRow, int fromCol, int toRow, int toCol) {
        // 必须直线移动
        if (fromRow != toRow && fromCol != toCol) {
            return false;
        }
        
        int pieceCount = 0;
        boolean isCapture = board[toRow][toCol] != EMPTY;
        
        if (fromRow == toRow) {
            int minCol = Math.min(fromCol, toCol);
            int maxCol = Math.max(fromCol, toCol);
            for (int col = minCol + 1; col < maxCol; col++) {
                if (board[fromRow][col] != EMPTY) {
                    pieceCount++;
                }
            }
        } else {
            int minRow = Math.min(fromRow, toRow);
            int maxRow = Math.max(fromRow, toRow);
            for (int row = minRow + 1; row < maxRow; row++) {
                if (board[row][fromCol] != EMPTY) {
                    pieceCount++;
                }
            }
        }
        
        // 吃子时需要有一个炮架，移动时不能有障碍
        if (isCapture) {
            return pieceCount == 1;
        } else {
            return pieceCount == 0;
        }
    }
    
    // 相/象移动规则
    private boolean isValidElephantMove(int fromRow, int fromCol, int toRow, int toCol) {
        int rowDiff = Math.abs(toRow - fromRow);
        int colDiff = Math.abs(toCol - fromCol);
        
        // 相走田
        if (rowDiff != 2 || colDiff != 2) {
            return false;
        }
        
        // 不能过河
        if ((isRedPiece(board[fromRow][fromCol]) && toRow < 5) || 
            (!isRedPiece(board[fromRow][fromCol]) && toRow > 4)) {
            return false;
        }
        
        // 检查象眼
        int eyeRow = (fromRow + toRow) / 2;
        int eyeCol = (fromCol + toCol) / 2;
        if (board[eyeRow][eyeCol] != EMPTY) {
            return false;
        }
        
        return true;
    }
    
    // 仕/士移动规则
    private boolean isValidAdvisorMove(int fromRow, int fromCol, int toRow, int toCol) {
        int rowDiff = Math.abs(toRow - fromRow);
        int colDiff = Math.abs(toCol - fromCol);
        
        // 士走斜线一步
        if (rowDiff != 1 || colDiff != 1) {
            return false;
        }
        
        // 不能出九宫
        if (toCol < 3 || toCol > 5) {
            return false;
        }
        
        if (isRedPiece(board[fromRow][fromCol])) {
            return toRow >= 7 && toRow <= 9;
        } else {
            return toRow >= 0 && toRow <= 2;
        }
    }
    
    // 将/帅移动规则
    private boolean isValidKingMove(int fromRow, int fromCol, int toRow, int toCol) {
        int rowDiff = Math.abs(toRow - fromRow);
        int colDiff = Math.abs(toCol - fromCol);
        
        // 将帅走直线一步
        if ((rowDiff == 1 && colDiff == 0) || (rowDiff == 0 && colDiff == 1)) {
            // 不能出九宫
            if (toCol < 3 || toCol > 5) {
                return false;
            }
            
            if (isRedPiece(board[fromRow][fromCol])) {
                return toRow >= 7 && toRow <= 9;
            } else {
                return toRow >= 0 && toRow <= 2;
            }
        }
        
        return false;
    }
    
    // 兵/卒移动规则
    private boolean isValidPawnMove(int fromRow, int fromCol, int toRow, int toCol, boolean isRed) {
        int rowDiff = toRow - fromRow;
        int colDiff = Math.abs(toCol - fromCol);
        
        // 红兵向上走，黑卒向下走
        if (isRed) {
            // 未过河只能前进
            if (fromRow > 4 && rowDiff != -1) {
                return false;
            }
            // 过河后可以左右前进
            if (fromRow <= 4 && !((rowDiff == -1 && colDiff == 0) || (rowDiff == 0 && colDiff == 1))) {
                return false;
            }
        } else {
            // 未过河只能前进
            if (fromRow < 5 && rowDiff != 1) {
                return false;
            }
            // 过河后可以左右前进
            if (fromRow >= 5 && !((rowDiff == 1 && colDiff == 0) || (rowDiff == 0 && colDiff == 1))) {
                return false;
            }
        }
        
        // 只能前进或横向移动一步
        return Math.abs(rowDiff) <= 1 && colDiff <= 1 && (rowDiff == 0 || colDiff == 0);
    }
    
    // 辅助方法：判断位置是否有效
    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < ROWS && col >= 0 && col < COLS;
    }
    
    // 辅助方法：判断棋子是否为红方
    private boolean isRedPiece(char piece) {
        return "车马相仕帅炮兵".indexOf(piece) >= 0;
    }
    
    // 游戏主循环
    public void play() {
        Scanner scanner = new Scanner(System.in);
        
        while (!gameOver) {
            printBoard();
            System.out.println("输入移动 (格式: 起点行 起点列 终点行 终点列), 例如: 9 0 8 0");
            System.out.print("> ");
            
            try {
                int fromRow = scanner.nextInt();
                int fromCol = scanner.nextInt();
                int toRow = scanner.nextInt();
                int toCol = scanner.nextInt();
                
                if (move(fromRow, fromCol, toRow, toCol)) {
                    System.out.println("移动成功!");
                } else {
                    System.out.println("无效移动! 请重试。");
                }
            } catch (Exception e) {
                System.out.println("输入格式错误! 请重试。");
                scanner.nextLine(); // 清除错误输入
            }
        }
        
        scanner.close();
    }
    
    public static void main(String[] args) {
        System.out.println("中国象棋游戏");
        System.out.println("---------------------------------");
        System.out.println("游戏规则:");
        System.out.println("1. 红方先行，轮流走棋");
        System.out.println("2. 输入格式: 起点行 起点列 终点行 终点列");
        System.out.println("3. 吃掉对方将/帅获胜");
        System.out.println("---------------------------------");
        
        ChineseChess game = new ChineseChess();
        game.play();
    }
}