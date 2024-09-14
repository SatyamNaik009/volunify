import axios from "axios";

export default class ApiService {
  static BASE_URL = "http://localhost:8081";

  static async registerUser(registration) {
    const response = await axios.post(
      `${this.BASE_URL}/auth/register`,
      registration
    );
    return response.data;
  }

  static async loginUser(loginDetails) {
    const response = await axios.post(
      `${this.BASE_URL}/auth/login`,
      loginDetails
    );
    return response.data;
  }

  static async getAllOpportunities() {
    const response = await axios.get(`${this.BASE_URL}/auth/getAll`, {});
    return response.data;
  }

  static async getAllOpportunitiesByCity() {
    const response = await axios.get(
      `${this.BASE_URL}/api/oppportunity/${city}`,
      {}
    );
    return response.data;
  }

  static async getAllCities() {
    const response = await axios.get(`${this.BASE_URL}/auth/getCities`, {});
    return response.data;
  }

  static async addOpportunity(opportunityRegister, file, userId) {
    const formData = new FormData();
    formData.append("opportunityRegister", JSON.stringify(opportunityRegister));
    if (file) {
      formData.append("file", file);
    }
    const response = await axios.post(
      `${this.BASE_URL}/api/admin/opportunity/add/${userId}`,
      formData,
      {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      }
    );
    return response.data;
  }

  static async getAllOpportunitiesOfUser(userId) {
    const response = await axios.get(
      `${this.BASE_URL}/api/user/opportunity/${userId}`,
      {}
    );
    return response.data;
  }

  static async getOpportunitiy(opportunityId) {
    const response = await axios.get(
      `${this.BASE_URL}/api/user/opportunity/get/${opportunityId}`,
      {}
    );
    return response.data;
  }

  static async addEnrollment(userId, opportunityId) {
    const response = await axios.post(
      `${this.BASE_URL}/api/user/${userId}/enroll/${opportunityId}`,
      {},
      {}
    );
    return response.data;
  }

  static logout() {
    localStorage.removeItem("name");
    localStorage.removeItem("id");
  }

  static isAuthenticated() {
    const token = localStorage.getItem("name");
    return !!token;
  }
}
