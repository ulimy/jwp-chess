export default class ChessService {
    constructor() {
        this.baseUrl = "http://localhost:8080/game";
        this.headers = {
            "Content-Type": "application/json"
        };
    }

    async moveSourceToTarget(moveRequest) {
        return await fetch(`${this.baseUrl}/move`, {
            method: "POST",
            headers: this.headers,
            body: JSON.stringify(moveRequest)
        }).then(response => response.json());
    }

    async startGame() {
        return await fetch(`${this.baseUrl}`, {
            method: "GET",
            headers: this.headers
        }).then(response => response.json());
    }

    async loadPrevGame() {
        const response = await fetch(`${this.baseUrl}/previous`);
        return response.json();
    }

}