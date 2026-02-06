import { VoteRequestModel } from "./vote-request.model";

export interface VoteEventModel {
    request: VoteRequestModel;
    callback: (status: string) => void;
}