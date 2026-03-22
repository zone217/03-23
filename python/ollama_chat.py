import ollama

def chat_with_model(prompt):
    """与本地模型对话"""
    response = ollama.chat(
        model='gemma3:4b',
        messages=[{'role': 'user', 'content': prompt}]
    )
    return response['message']['content']

# 使用示例
if __name__ == '__main__':
    print("=== Ollama Python 示例 ===")
    
    # 简单对话
    result = chat_with_model('你好，请介绍一下自己')
    print(f"回复：{result}")
    
    # 代码生成
    code_result = chat_with_model('用 Python 写一个快速排序函数')
    print(f"\n代码生成：{code_result}")
    
    # 流式输出
    print("\n=== 流式输出 ===")
    stream = ollama.chat(
        model='gemma3:4b',
        messages=[{'role': 'user', 'content': '讲一个笑话'}],
        stream=True
    )
    for chunk in stream:
        print(chunk['message']['content'], end='', flush=True)
    print()
