import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ChineseChessGame extends Application {
    
    // 棋盘和棋子相关常量
    private static final int BOARD_SIZE = 9;
    private static final int CELL_SIZE = 60;
    private static final int PIECE_RADIUS = 25;
    private static final int BOARD_WIDTH = BOARD_SIZE * CELL_SIZE;
    private static final int BOARD_HEIGHT = 10 * CELL_SIZE;
    
    // 游戏状态
    private boolean gameActive = true;
    private boolean redTurn = true; // 红方先行
    private Piece selectedPiece = null;
    
    // 棋盘数据
    private Piece[][] board = new Piece[BOARD_SIZE][10];
    
    // UI组件
    private Canvas canvas;
    private GraphicsContext gc;
    private Label statusLabel;
    
    @Override
    public void start(Stage primaryStage) {
        // 初始化棋盘
        initializeBoard();
        
        // 创建主布局
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #f0d9b5;");
        
        // 创建棋盘画布
        canvas = new Canvas(BOARD_WIDTH, BOARD_HEIGHT);
        gc = canvas.getGraphicsContext2D();
        drawBoard();
        
        // 事件处理：点击选择棋子
        canvas.setOnMouseClicked(e -> {
            if (!gameActive) return;
            
            int x = (int) (e.getX() / CELL_SIZE);
            int y = (int) (e.getY() / CELL_SIZE);
            
            // 边界检查
            if (x < 0 || x >= BOARD_SIZE || y < 0 || y >= 10) return;
            
            // 如果没有选中的棋子
            if (selectedPiece == null) {
                // 选择棋子（只能选择当前回合方的棋子）
                Piece piece = board[x][y];
                if (piece != null && piece.red == redTurn) {
                    selectedPiece = piece;
                    drawBoard();
                }
            } 
            // 如果已经有选中的棋子
            else {
                // 移动棋子
                if (isValidMove(selectedPiece, x, y)) {
                    movePiece(selectedPiece, x, y);
                    redTurn = !redTurn;
                    updateStatus();
                    
                    // 检查游戏是否结束
                    checkGameEnd();
                }
                selectedPiece = null;
                drawBoard();
            }
        });
        
        // 创建状态栏
        statusLabel = new Label("红方回合");
        statusLabel.setFont(Font.font(16));
        statusLabel.setPadding(new Insets(10));
        
        // 创建控制按钮
        Button restartButton = new Button("重新开始");
        restartButton.setStyle("-fx-background-color: #8b4513; -fx-text-fill: white;");
        restartButton.setOnAction(e -> restartGame());
        
        Button exitButton = new Button("退出游戏");
        exitButton.setStyle("-fx-background-color: #8b4513; -fx-text-fill: white;");
        exitButton.setOnAction(e -> primaryStage.close());
        
        HBox buttonBox = new HBox(20, restartButton, exitButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(10));
        
        VBox controlPanel = new VBox(10, statusLabel, buttonBox);
        controlPanel.setAlignment(Pos.CENTER);
        controlPanel.setPadding(new Insets(10));
        
        // 布局设置
        root.setCenter(canvas);
        root.setBottom(controlPanel);
        
        // 创建场景
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT + 120);
        primaryStage.setTitle("中国象棋");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    // 初始化棋盘
    private void initializeBoard() {
        // 初始化红方棋子
        board[0][9] = new Piece("车", true, 0, 9);
        board[1][9] = new Piece("马", true, 1, 9);
        board[2][9] = new Piece("相", true, 2, 9);
        board[3][9] = new Piece("士", true, 3, 9);
        board[4][9] = new Piece("帅", true, 4, 9);
        board[5][9] = new Piece("士", true, 5, 9);
        board[6][9] = new Piece("相", true, 6, 9);
        board[7][9] = new Piece("马", true, 7, 9);
        board[8][9] = new Piece("车", true, 8, 9);
        board[1][7] = new Piece("炮", true, 1, 7);
        board[7][7] = new Piece("炮", true, 7, 7);
        board[0][6] = new Piece("兵", true, 0, 6);
        board[2][6] = new Piece("兵", true, 2, 6);
        board[4][6] = new Piece("兵", true, 4, 6);
        board[6][6] = new Piece("兵", true, 6, 6);
        board[8][6] = new Piece("兵", true, 8, 6);
        
        // 初始化黑方棋子
        board[0][0] = new Piece("车", false, 0, 0);
        board[1][0] = new Piece("马", false, 1, 0);
        board[2][0] = new Piece("象", false, 2, 0);
        board[3][0] = new Piece("士", false, 3, 0);
        board[4][0] = new Piece("将", false, 4, 0);
        board[5][0] = new Piece("士", false, 5, 0);
        board[6][0] = new Piece("象", false, 6, 0);
        board[7][0] = new Piece("马", false, 7, 0);
        board[8][0] = new Piece("车", false, 8, 0);
        board[1][2] = new Piece("炮", false, 1, 2);
        board[7][2] = new Piece("炮", false, 7, 2);
        board[0][3] = new Piece("卒", false, 0, 3);
        board[2][3] = new Piece("卒", false, 2, 3);
        board[4][3] = new Piece("卒", false, 4, 3);
        board[6][3] = new Piece("卒", false, 6, 3);
        board[8][3] = new Piece("卒", false, 8, 3);
    }
    
    // 绘制棋盘
    private void drawBoard() {
        gc.clearRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
        
        // 绘制棋盘背景
        gc.setFill(Color.BURLYWOOD);
        gc.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);
        
        // 绘制网格线
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        
        // 绘制横线
        for (int i = 0; i <= 9; i++) {
            int y = i * CELL_SIZE;
            gc.strokeLine(CELL_SIZE, y, BOARD_WIDTH - CELL_SIZE, y);
        }
        
        // 绘制竖线
        for (int i = 0; i < 9; i++) {
            int x = i * CELL_SIZE;
            // 上半部分
            gc.strokeLine(x, 0, x, 4 * CELL_SIZE);
            // 下半部分
            gc.strokeLine(x, 5 * CELL_SIZE, x, 9 * CELL_SIZE);
        }
        
        // 绘制"楚河汉界"
        gc.setFill(Color.BLACK);
        gc.setFont(Font.font(20));
        gc.fillText("楚 河        汉 界", BOARD_WIDTH / 2 - 70, 5 * CELL_SIZE - 10);
        
        // 绘制九宫格斜线
        gc.strokeLine(3 * CELL_SIZE, 0, 5 * CELL_SIZE, 2 * CELL_SIZE);
        gc.strokeLine(5 * CELL_SIZE, 0, 3 * CELL_SIZE, 2 * CELL_SIZE);
        gc.strokeLine(3 * CELL_SIZE, 7 * CELL_SIZE, 5 * CELL_SIZE, 9 * CELL_SIZE);
        gc.strokeLine(5 * CELL_SIZE, 7 * CELL_SIZE, 3 * CELL_SIZE, 9 * CELL_SIZE);
        
        // 绘制棋子
        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < 10; y++) {
                if (board[x][y] != null) {
                    drawPiece(board[x][y], x, y);
                }
            }
        }
        
        // 绘制选中的棋子
        if (selectedPiece != null) {
            drawSelectedPiece(selectedPiece);
        }
    }
    
    // 绘制棋子
    private void drawPiece(Piece piece, int x, int y) {
        int centerX = x * CELL_SIZE + CELL_SIZE / 2;
        int centerY = y * CELL_SIZE + CELL_SIZE / 2;
        
        // 绘制棋子背景
        gc.setFill(piece.red ? Color.RED : Color.BLACK);
        gc.fillOval(centerX - PIECE_RADIUS, centerY - PIECE_RADIUS, 
                    PIECE_RADIUS * 2, PIECE_RADIUS * 2);
        
        // 绘制棋子边框
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.strokeOval(centerX - PIECE_RADIUS, centerY - PIECE_RADIUS, 
                      PIECE_RADIUS * 2, PIECE_RADIUS * 2);
        
        // 绘制棋子文字
        gc.setFill(piece.red ? Color.WHITE : Color.WHITE);
        gc.setFont(Font.font(20));
        gc.fillText(piece.type, centerX - 10, centerY + 8);
    }
    
    // 绘制选中的棋子
    private void drawSelectedPiece(Piece piece) {
        int centerX = piece.x * CELL_SIZE + CELL_SIZE / 2;
        int centerY = piece.y * CELL_SIZE + CELL_SIZE / 2;
        
        // 绘制选中效果
        gc.setStroke(Color.YELLOW);
        gc.setLineWidth(2);
        gc.strokeOval(centerX - PIECE_RADIUS, centerY - PIECE_RADIUS, 
                      PIECE_RADIUS * 2, PIECE_RADIUS * 2);
    }
    
    // 检查移动是否有效
    private boolean isValidMove(Piece piece, int targetX, int targetY) {
        // 目标位置是否有己方棋子
        Piece targetPiece = board[targetX][targetY];
        if (targetPiece != null && targetPiece.red == piece.red) {
            return false;
        }
        
        // 根据棋子类型判断移动规则
        switch (piece.type) {
            case "车":
                return isValidRookMove(piece, targetX, targetY);
            case "马":
                return isValidKnightMove(piece, targetX, targetY);
            case "炮":
                return isValidCannonMove(piece, targetX, targetY);
            case "相": case "象":
                return isValidBishopMove(piece, targetX, targetY);
            case "士": case "仕":
                return isValidAdvisorMove(piece, targetX, targetY);
            case "帅": case "将":
                return isValidKingMove(piece, targetX, targetY);
            case "兵": case "卒":
                return isValidPawnMove(piece, targetX, targetY);
            default:
                return false;
        }
    }
    
    // 车移动规则
    private boolean isValidRookMove(Piece piece, int targetX, int targetY) {
        if (piece.x != targetX && piece.y != targetY) {
            return false; // 车只能直行
        }
        
        if (piece.x == targetX) {
            // 垂直移动
            int startY = Math.min(piece.y, targetY) + 1;
            int endY = Math.max(piece.y, targetY);
            for (int y = startY; y < endY; y++) {
                if (board[piece.x][y] != null) {
                    return false; // 路径上有棋子
                }
            }
        } else {
            // 水平移动
            int startX = Math.min(piece.x, targetX) + 1;
            int endX = Math.max(piece.x, targetX);
            for (int x = startX; x < endX; x++) {
                if (board[x][piece.y] != null) {
                    return false; // 路径上有棋子
                }
            }
        }
        
        return true;
    }
    
    // 马移动规则
    private boolean isValidKnightMove(Piece piece, int targetX, int targetY) {
        int dx = Math.abs(targetX - piece.x);
        int dy = Math.abs(targetY - piece.y);
        
        // 马走日
        if (!((dx == 1 && dy == 2) || (dx == 2 && dy == 1))) {
            return false;
        }
        
        // 检查马腿
        if (dx == 1) {
            // 纵向移动
            int blockY = piece.y + (targetY > piece.y ? 1 : -1);
            if (board[piece.x][blockY] != null) {
                return false; // 马腿被挡
            }
        } else {
            // 横向移动
            int blockX = piece.x + (targetX > piece.x ? 1 : -1);
            if (board[blockX][piece.y] != null) {
                return false; // 马腿被挡
            }
        }
        
        return true;
    }
    
    // 炮移动规则
    private boolean isValidCannonMove(Piece piece, int targetX, int targetY) {
        if (piece.x != targetX && piece.y != targetY) {
            return false; // 炮只能直行
        }
        
        int pieceCount = 0;
        
        if (piece.x == targetX) {
            // 垂直移动
            int startY = Math.min(piece.y, targetY) + 1;
            int endY = Math.max(piece.y, targetY);
            for (int y = startY; y < endY; y++) {
                if (board[piece.x][y] != null) {
                    pieceCount++;
                }
            }
        } else {
            // 水平移动
            int startX = Math.min(piece.x, targetX) + 1;
            int endX = Math.max(piece.x, targetX);
            for (int x = startX; x < endX; x++) {
                if (board[x][piece.y] != null) {
                    pieceCount++;
                }
            }
        }
        
        // 炮需要翻山吃子
        Piece targetPiece = board[targetX][targetY];
        if (targetPiece == null) {
            // 移动时不能有棋子阻挡
            return pieceCount == 0;
        } else {
            // 吃子时需要且只能翻过一个棋子
            return pieceCount == 1;
        }
    }
    
    // 相/象移动规则
    private boolean isValidBishopMove(Piece piece, int targetX, int targetY) {
        int dx = Math.abs(targetX - piece.x);
        int dy = Math.abs(targetY - piece.y);
        
        // 相走田
        if (dx != 2 || dy != 2) {
            return false;
        }
        
        // 检查象眼
        int blockX = (piece.x + targetX) / 2;
        int blockY = (piece.y + targetY) / 2;
        if (board[blockX][blockY] != null) {
            return false; // 象眼被挡
        }
        
        // 相不能过河
        if (piece.red && targetY < 5) {
            return false;
        }
        if (!piece.red && targetY > 4) {
            return false;
        }
        
        return true;
    }
    
    // 士/仕移动规则
    private boolean isValidAdvisorMove(Piece piece, int targetX, int targetY) {
        int dx = Math.abs(targetX - piece.x);
        int dy = Math.abs(targetY - piece.y);
        
        // 士走斜线
        if (dx != 1 || dy != 1) {
            return false;
        }
        
        // 士不能出九宫格
        if (targetX < 3 || targetX > 5) {
            return false;
        }
        
        if (piece.red) {
            return targetY >= 7 && targetY <= 9;
        } else {
            return targetY >= 0 && targetY <= 2;
        }
    }
    
    // 将/帅移动规则
    private boolean isValidKingMove(Piece piece, int targetX, int targetY) {
        int dx = Math.abs(targetX - piece.x);
        int dy = Math.abs(targetY - piece.y);
        
        // 将帅走一步
        if ((dx == 1 && dy == 0) || (dx == 0 && dy == 1)) {
            // 将帅不能出九宫格
            if (targetX < 3 || targetX > 5) {
                return false;
            }
            
            if (piece.red) {
                return targetY >= 7 && targetY <= 9;
            } else {
                return targetY >= 0 && targetY <= 2;
            }
        }
        
        // 将帅对面
        if (piece.x == targetX && board[targetX][targetY] != null && 
            board[targetX][targetY].type.equals(piece.red ? "将" : "帅")) {
            
            // 检查中间是否有棋子
            int startY = Math.min(piece.y, targetY) + 1;
            int endY = Math.max(piece.y, targetY);
            for (int y = startY; y < endY; y++) {
                if (board[piece.x][y] != null) {
                    return false;
                }
            }
            return true;
        }
        
        return false;
    }
    
    // 兵/卒移动规则
    private boolean isValidPawnMove(Piece piece, int targetX, int targetY) {
        int dx = Math.abs(targetX - piece.x);
        int dy = targetY - piece.y;
        
        if (piece.red) {
            // 红方兵
            if (piece.y > 4) { // 未过河
                if (dx == 0 && dy == -1) {
                    return true; // 只能向前
                }
            } else { // 已过河
                if ((dx == 0 && dy == -1) || (dx == 1 && dy == 0)) {
                    return true; // 可以向前或左右
                }
            }
        } else {
            // 黑方卒
            if (piece.y < 5) { // 未过河
                if (dx == 0 && dy == 1) {
                    return true; // 只能向前
                }
            } else { // 已过河
                if ((dx == 0 && dy == 1) || (dx == 1 && dy == 0)) {
                    return true; // 可以向前或左右
                }
            }
        }
        
        return false;
    }
    
    // 移动棋子
    private void movePiece(Piece piece, int targetX, int targetY) {
        // 移除目标位置的棋子（如果有）
        board[targetX][targetY] = null;
        
        // 更新原位置
        board[piece.x][piece.y] = null;
        
        // 更新棋子位置
        piece.x = targetX;
        piece.y = targetY;
        board[targetX][targetY] = piece;
    }
    
    // 更新游戏状态
    private void updateStatus() {
        statusLabel.setText(redTurn ? "红方回合" : "黑方回合");
    }
    
    // 检查游戏是否结束
    private void checkGameEnd() {
        boolean redKingFound = false;
        boolean blackKingFound = false;
        
        // 检查将帅是否存在
        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < 10; y++) {
                Piece piece = board[x][y];
                if (piece != null) {
                    if (piece.type.equals("帅")) {
                        redKingFound = true;
                    } else if (piece.type.equals("将")) {
                        blackKingFound = true;
                    }
                }
            }
        }
        
        // 如果一方将帅被吃，游戏结束
        if (!redKingFound) {
            gameActive = false;
            statusLabel.setText("游戏结束！黑方胜利！");
        } else if (!blackKingFound) {
            gameActive = false;
            statusLabel.setText("游戏结束！红方胜利！");
        }
    }
    
    // 重新开始游戏
    private void restartGame() {
        // 重置游戏状态
        gameActive = true;
        redTurn = true;
        selectedPiece = null;
        
        // 清空棋盘
        for (int x = 0; x < BOARD_SIZE; x++) {
            for (int y = 0; y < 10; y++) {
                board[x][y] = null;
            }
        }
        
        // 重新初始化棋盘
        initializeBoard();
        
        // 更新状态
        updateStatus();
        
        // 重绘棋盘
        drawBoard();
    }
    
    // 棋子类
    private static class Piece {
        String type; // 棋子类型
        boolean red; // 是否为红方
        int x, y;    // 位置
        
        Piece(String type, boolean red, int x, int y) {
            this.type = type;
            this.red = red;
            this.x = x;
            this.y = y;
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}